package generator.dao.db;

import generator.models.Person;
import generator.utils.DbHelper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUser {

    private Connection connection;
    private Logger log = Logger.getLogger(DBUser.class);

    public DBUser(Connection connection) {
        this.connection = connection;
    }

    public void saveUser(Person person){
        int id = getUserIdByFullName(person.getName(), person.getSurname(), person.getSecondname());
        if(id!=0){
            log.info("Пользователь '"+person.getSurname()+" "+person.getName()+" "+person.getSecondname()+"' уже существует. " +
                    "Обновляются остальные поля.");
            updateUser(id, person);
        } else {
            insertUser(person);
        }
    }

    public void saveUsersList(List<Person> people) {
        for (Person person : people) {
            saveUser(person);
        }
    }

    private int getUserIdByFullName(String name, String surname, String secondName){
        try {
            String query = "SELECT * FROM users WHERE last_name=? AND first_name=? AND patronymic=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,surname);
            statement.setString(2,name);
            statement.setString(3,secondName);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateUser(int id, Person person){
        try {
            String sql = "UPDATE users SET age = ?,birthday = ?, postal_code = ?, country = ?, " +
                    "region = ?, city = ?, street = ?, house = ?, appartment = ?, inn = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, person.getAge());
            statement.setDate(2, DbHelper.convertDate(person.getBirthday()));
            statement.setString(3, person.getPostalCode());
            statement.setString(4, person.getCountry());
            statement.setString(5, person.getRegion());
            statement.setString(6,  person.getCity());
            statement.setString(7, person.getStreet());
            statement.setInt(8, person.getHouse());
            statement.setInt(9, person.getAppartment());
            statement.setString(10, person.getInn());
            statement.setInt(11, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertUser(Person person) {
        try{
            String sql = "INSERT INTO `users` " +
                    " (`gender`, `last_name`, `first_name`, `patronymic`, `age`, `birthday`, `postal_code`, " +
                    "`country`, `region`, `city`, `street`, `house`, `appartment`, `inn`) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, person.getGender());
            statement.setString(2, person.getSurname());
            statement.setString(3, person.getName());
            statement.setString(4, person.getSecondname());
            statement.setInt(5, person.getAge());
            statement.setDate(6, DbHelper.convertDate(person.getBirthday()));
            statement.setString(7, person.getPostalCode());
            statement.setString(8, person.getCountry());
            statement.setString(9, person.getRegion());
            statement.setString(10, person.getCity());
            statement.setString(11, person.getStreet());
            statement.setInt(12, person.getHouse());
            statement.setInt(13, person.getAppartment());
            statement.setString(14, person.getInn());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
