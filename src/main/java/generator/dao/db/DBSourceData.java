package generator.dao.db;

import generator.utils.Source;
import generator.utils.NoResultException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSourceData implements Source {

    private static final String CITIES_TABLE_NAME = "cities";
    private static final String COUNTRIES_TABLE_NAME = "countries";
    private static final String REGIONS_TABLE_NAME = "regions";
    private static final String STREETS = "streets";
    private static final String FEMALE_NAMES_TABLE_NAME = "names_female";
    private static final String MALE_NAMES_TABLE_NAME = "names_male";
    private static final String SURNAMES_TABLE_NAME = "surnames_root";
    private static final String SECOND_NAMES_TABLE_NAME = "second_names_root";

    private Connection connection;

    public DBSourceData(Connection connection) {
        this.connection = connection;
    }

    public String getRandomCity() {
        return getRandomValue(CITIES_TABLE_NAME);
    }

    public  String getRandomCountry() {
        return getRandomValue(COUNTRIES_TABLE_NAME);
    }

    public  String getRandomRegion() {
        return getRandomValue(REGIONS_TABLE_NAME);
    }

    public  String getRandomStreet() {
        return getRandomValue(STREETS);
    }

    public  String getRandomFemaleName() {
        return getRandomValue(FEMALE_NAMES_TABLE_NAME);
    }

    public  String getRandomMaleName() {
        return getRandomValue(MALE_NAMES_TABLE_NAME);
    }

    public  String getRandomSurname() {
        return getRandomValue(SURNAMES_TABLE_NAME);
    }

    public  String getRandomSecondName() {
        return getRandomValue(SECOND_NAMES_TABLE_NAME);
    }

    private String getRandomValue(String tableName) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM "+tableName+" ORDER BY RAND() LIMIT 0,1;";
            ResultSet result = statement.executeQuery(query);
            if (!result.isBeforeFirst() ) {
                throw new NoResultException("Данные в таблице `"+tableName+"` отсутствуют.");
            }
            if (result.next()){
                return result.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
