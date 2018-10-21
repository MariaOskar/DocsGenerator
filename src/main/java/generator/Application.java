package generator;

import com.mashape.unirest.http.exceptions.UnirestException;
import generator.adapters.ExcelAdapter;
import generator.adapters.PDFAdapter;
import generator.api.API;
import generator.components.Table;
import generator.dao.db.DBSourceData;
import generator.dao.db.DBUser;
import generator.dao.file.FileSourceData;
import generator.utils.*;
import generator.models.Person;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Application {

    private static Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        int quantity = GeneratorHelper.randomNum(1,30);
        try {
            Connection connection = DbHelper.getConnection();
            try {
                people = API.getPersonList(quantity);
            } catch (UnirestException e) {
                if(e.getMessage().contains("UnknownHostException") || e.getMessage().contains("HttpHostConnectException")){
                    log.warn("Сеть отсутствует. Генерим пользователей из БД");
                    try {
                        people = generateSomePeopleWith(new DBSourceData(connection), quantity);
                    } catch (NoResultException ex) {
                        log.warn("Данные для генерации в БД отсутствуют. Генерим пользователей своими силами.");
                        people = generateSomePeopleWith(new FileSourceData(), quantity);
                    }
                }
            }

            DBUser dbUser = new DBUser(connection);
            dbUser.saveUsersList(people);

        } catch (SQLException se) {
            log.warn("Мы не смогли подключиться к базе данных. Генерим пользователей своими силами.");
            people = generateSomePeopleWith(new FileSourceData(), quantity);
        }

        Table<Person> personTable = new Table<>(Person.class, people);

        personTable
                .setAdapter(new ExcelAdapter())
                .create();

        personTable
                .setAdapter(new PDFAdapter())
                .create();

    }

    private static List<Person> generateSomePeopleWith(Source dataSource, int quantity){
        DataGenerator generator = new DataGenerator(dataSource);
        return generator.generateSomePeople(quantity);
    }

}
