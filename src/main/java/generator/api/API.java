package generator.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import generator.models.Person;

import java.lang.reflect.Type;
import java.util.List;

public class API {

    private static final String API_DOMAIN = "http://api.randomuser.oskerko.ru/";

    public static List<Person> getPersonList(int quantity) throws UnirestException {
        String response = Unirest
                               .get(API_DOMAIN + "users")
                                .queryString("quantity", quantity)
                                .asString()
                                .getBody();
        Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonGsonAdapter()).create();
        Type type = new TypeToken<List<Person>>(){}.getType();
        return gson.fromJson(response,type);
    }

}
