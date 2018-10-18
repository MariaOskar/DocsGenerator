package generator;

import com.mashape.unirest.http.exceptions.UnirestException;
import generator.adapters.ExcelAdapter;
import generator.api.API;
import generator.utils.DataGenerator;
import generator.adapters.PDFAdapter;
import generator.models.Person;
import generator.components.Table;
import generator.utils.GeneratorHelper;
import org.apache.log4j.Logger;

import java.util.List;


public class Application {
    private static Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        List<Person> people;
        int quantity = GeneratorHelper.randomNum(1,30);

        try {
            people = API.getPersonList(quantity);
        } catch (UnirestException e) {
            if(e.getMessage().contains("UnknownHostException") || e.getMessage().contains("HttpHostConnectException")){
                log.warn("Сеть отсутствует. Генерим пользователей своими силами.");
            }
            people = DataGenerator.generateSomePeople(quantity);
        }

        Table<Person> personTable = new Table<>(Person.class, people);

        personTable
                .setAdapter(new ExcelAdapter())
                .create();

        personTable
                .setAdapter(new PDFAdapter())
                .create();



    }
}
