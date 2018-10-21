package generator.utils;

import generator.models.Person;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataGenerator {

    private static final String MOSCOW_REGION_CODE = "77";
    private Source dataSource;

    public DataGenerator() {
    }

    public DataGenerator(Source dataSource) {
        this.dataSource = dataSource;
    }

    public Source getDataSource() {
        return dataSource;
    }

    public DataGenerator setDataSource(Source dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    private static Integer[] addLastNumsInINN(String numbers) {
        Integer[] nums = new Integer[12];
        String[] parts = numbers.split("");
        for (int i=0; i < parts.length; i++) {
            nums[i] = Integer.valueOf(parts[i]);
        }

        nums[10] = ((nums[0]*7 + nums[1]*2 + nums[2]*4 + nums[3]*10 + nums[4]*3 + nums[5]*5 + nums[6]*9 + nums[7]*4 + nums[8]*6 + nums[9]*8) % 11) % 10;
        nums[11] = ((nums[0]*3 + nums[1]*7 + nums[2]*2 + nums[3]*4 + nums[4]*10 + nums[5]*3 + nums[6]*5 + nums[7]*9 + nums[8]*4 + nums[9]*6 + nums[10]*8) % 11) % 10;
        return nums;
    }

    private static String getInspectionNum(){
        String inspectionNum = MOSCOW_REGION_CODE;
        int rand = GeneratorHelper.randomNum(1,51);
        if(rand<10) inspectionNum+="0"+rand;
        else inspectionNum+=rand;
        return inspectionNum;
    }

    public static String generateINN() {
        String numbers = getInspectionNum() + String.valueOf(GeneratorHelper.getRandomNums(6));
        Integer[] inn = addLastNumsInINN(numbers);
        StringBuilder builder = new StringBuilder();
        for (Integer x : inn) {
            builder.append(x);
        }
        return builder.toString();
    }

    public static Date generateBirthday() {
        GregorianCalendar calendar = new GregorianCalendar();

        int year = GeneratorHelper.randomNum(1920, 2000);
        calendar.set(GregorianCalendar.YEAR, year);

        int day = GeneratorHelper.randomNum(1, calendar.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        calendar.set(GregorianCalendar.DAY_OF_YEAR, day);

        return calendar.getTime();
    }

    public static String generateGender() {
        if (GeneratorHelper.randomNum(0, 1) > 0) return "Ж";
        else return "М";
    }

    public static int generateHouse(){
        return GeneratorHelper.randomNum(1,99);
    }

    public static int generateAppartment(){
        return GeneratorHelper.randomNum(1,150);
    }

    public static String generatePostCode() {
        return GeneratorHelper.getRandomNums(6);
    }


    public Person generatePersonalData(){
        Person person = new Person();
        person.setGender(generateGender());
        person.setSurname(dataSource.getRandomSurname());
        if(person.getGender().equals("Ж")){
            person.setSurname( person.getSurname() + "а" ) ;
            person.setName(dataSource.getRandomFemaleName());
            person.setSecondname(dataSource.getRandomSecondName()+"на");
        } else
        {
            person.setName(dataSource.getRandomMaleName());
            person.setSecondname(dataSource.getRandomSecondName()+"ич");
        }
        person.setBirthday(generateBirthday());
        person.setAge(GeneratorHelper.calculateAge(person.getBirthday()));
        person.setInn(generateINN());
        person.setPostalCode(generatePostCode());
        person.setCountry(dataSource.getRandomCountry());
        person.setRegion(dataSource.getRandomRegion());
        person.setCity(dataSource.getRandomCity());
        person.setStreet(dataSource.getRandomStreet());
        person.setHouse(generateHouse());
        person.setAppartment(generateAppartment());

        return person;
    }

    public List<Person> generateSomePeople(int quantity){
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i<quantity; i++){
            personList.add(generatePersonalData());
        }
        return personList;
    }

    public static String filenameGenerator(Class objClass, String format){
        Date now = new Date();
        String folder = PropertyHelper.getProperty("application.properties", "output.dir");
        String name = ((Title)objClass.getAnnotation(Title.class)).value();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MM-yy_HH-mm-ss");
        return folder + File.separator + name + "_" + dateFormat.format(now) + "." + format;
    }

}

