package generator.models;

import generator.utils.GeneratorHelper;
import generator.utils.Preparable;
import generator.utils.Title;

import java.util.Date;

@Title("Персональные данные")
public class Person implements Preparable {

    @Title("Имя")
    private String name;
    @Title("Фамилия")
    private String surname;
    @Title("Отчество")
    private String secondname;
    @Title("Возраст")
    private int age;
    @Title("Пол")
    private String gender;
    @Title("Дата рождения")
    private Date birthday;
    @Title("ИНН")
    private String inn;
    @Title("Индекс")
    private String postalCode;
    @Title("Страна")
    private String country;
    @Title("Регион")
    private String region;
    @Title("Город")
    private String city;
    @Title("Улица")
    private String street;
    @Title("Дом")
    private int house;
    @Title("Квартира")
    private int appartment;

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Person setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getSecondname() {
        return secondname;
    }

    public Person setSecondname(String secondname) {
        this.secondname = secondname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Person setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getInn() {
        return inn;
    }

    public Person setInn(String inn) {
        this.inn = inn;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Person setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Person setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Person setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Person setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Person setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getHouse() {
        return house;
    }

    public Person setHouse(int house) {
        this.house = house;
        return this;
    }

    public int getAppartment() {
        return appartment;
    }

    public Person setAppartment(int appartment) {
        this.appartment = appartment;
        return this;
    }

    public Object[] prepareData(){
        Object[] data = {this.name,
                this.surname,
                this.secondname,
                this.age,
                this.gender,
                GeneratorHelper.formatBirthday(this.birthday),
                this.inn,
                this.postalCode,
                this.country,
                this.region,
                this.city,
                this.street,
                this.house,
                this.appartment};
        return data;
    }

}
