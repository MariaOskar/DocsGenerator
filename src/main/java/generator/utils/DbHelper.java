package generator.utils;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Properties;

public class DbHelper {

    private static final String ENCODING = "UTF-8";

    public static String getDbUrl(){
        String host = PropertyHelper.getProperty("application.properties","db.host");
        String port = PropertyHelper.getProperty("application.properties","db.port");
        String name = PropertyHelper.getProperty("application.properties","db.name");
        return  "jdbc:mysql://"+host+":"+port+"/"+name;
    }

    public static Date convertDate(java.util.Date date){
        return Date.valueOf( date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static java.sql.Connection getConnection() throws SQLException {
        Properties properties=new Properties();
        properties.setProperty("user",PropertyHelper.getProperty("application.properties","db.user"));
        properties.setProperty("password",PropertyHelper.getProperty("application.properties","db.password"));
        properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding", ENCODING);
        return DriverManager.getConnection(getDbUrl(),properties);
    }
}
