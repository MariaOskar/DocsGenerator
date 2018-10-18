package generator.api;

import com.google.gson.*;
import generator.models.Person;
import generator.utils.DataGenerator;
import generator.utils.GeneratorHelper;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonGsonAdapter implements JsonSerializer<Person>, JsonDeserializer<Person> {

    private static final String FULL_NAME = "fullName";
    private static final String DOB = "doB";
    private static final String LOCATION = "location";
    private static final String GENDER = "gender";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PATRONYMIC = "patronymic";
    private static final String BIRTHDAY = "birthday";
    private static final String AGE = "age";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String REGION = "region";
    private static final String STREET = "street";
    private static final String HOUSE = "house";
    private static final String APPARTMENT = "appartment";
    private static final String POSTAL_CODE = "postalCode";
    private static final String INN = "inn";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Override
    public Person deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Person person = new Person();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        JsonObject json = jsonElement.getAsJsonObject();

        JsonObject fullName = json.get(FULL_NAME).getAsJsonObject();
        JsonObject dob = json.get(DOB).getAsJsonObject();
        JsonObject location = json.get(LOCATION).getAsJsonObject();

        person.setGender(json.get(GENDER).getAsString());
        person.setName(fullName.get(FIRST_NAME).getAsString());
        person.setSurname(fullName.get(LAST_NAME).getAsString());
        person.setSecondname(fullName.get(PATRONYMIC).getAsString());
        try {
            person.setBirthday(dateFormat.parse(dob.get(BIRTHDAY).getAsString()));
            person.setAge(dob.get(AGE).getAsInt());
        } catch (ParseException e) {
           person.setBirthday(DataGenerator.generateBirthday());
           person.setAge(GeneratorHelper.calculateAge(person.getBirthday()));
        }
        person.setCountry(location.get(COUNTRY).getAsString());
        person.setCity(location.get(CITY).getAsString());
        person.setRegion(location.get(REGION).getAsString());
        person.setStreet(location.get(STREET).getAsString());
        person.setHouse(location.get(HOUSE).getAsInt());
        person.setAppartment(location.get(APPARTMENT).getAsInt());
        person.setPostalCode(location.get(POSTAL_CODE).getAsString());
        person.setInn(json.get(INN).getAsString());
        return person;
    }

    @Override
    public JsonElement serialize(Person person, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();

        JsonObject fullNameJson = new JsonObject();
        fullNameJson.addProperty(LAST_NAME, person.getSurname());
        fullNameJson.addProperty(FIRST_NAME, person.getName());
        fullNameJson.addProperty(PATRONYMIC, person.getSecondname());

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        JsonObject doBJson = new JsonObject();
        doBJson.addProperty(AGE, person.getAge());
        doBJson.addProperty(BIRTHDAY, dateFormat.format(person.getBirthday()));

        JsonObject locationJson = new JsonObject();
        locationJson.addProperty(POSTAL_CODE, person.getPostalCode());
        locationJson.addProperty(COUNTRY,person.getCountry());
        locationJson.addProperty(REGION, person.getRegion());
        locationJson.addProperty(CITY, person.getCity());
        locationJson.addProperty(STREET, person.getStreet());
        locationJson.addProperty(HOUSE, person.getHouse());
        locationJson.addProperty(APPARTMENT, person.getAppartment());

        json.addProperty(GENDER, person.getGender());
        json.add(FULL_NAME, fullNameJson );
        json.add(DOB, doBJson);
        json.add(LOCATION, locationJson);
        json.addProperty(INN, person.getInn());

        return json;
    }
}


