package generator.dao.file;

import generator.utils.Source;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static generator.utils.GeneratorHelper.randomNum;

public class FileSourceData implements Source {

    private static final String DS = File.separator;
    private static final String SURNAMES_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"Surnames.txt";
    private static final String MALE_NAMES_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"NamesMale.txt";
    private static final String FEMALE_NAMES_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"NamesFemale.txt";
    private static final String SECOND_NAMES_ROOT_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"SecondNamesRoot.txt";
    private static final String COUNTRIES_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"Countries.txt";
    private static final String REGIONS_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"Regions.txt";
    private static final String CITIES_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"Cities.txt";
    private static final String STREETS_LIST_FILENAME = "src"+DS+"main"+DS+"resources"+DS+"data"+DS+"Streets.txt";


    @Override
    public String getRandomCity() {
        return chooseRandomString(CITIES_LIST_FILENAME);
    }

    @Override
    public String getRandomCountry() {
        return chooseRandomString(COUNTRIES_LIST_FILENAME);
    }

    @Override
    public String getRandomRegion() {
        return chooseRandomString(REGIONS_LIST_FILENAME);
    }

    @Override
    public String getRandomStreet() {
        return chooseRandomString(STREETS_LIST_FILENAME);
    }

    @Override
    public String getRandomFemaleName() {
        return chooseRandomString(FEMALE_NAMES_LIST_FILENAME);
    }

    @Override
    public String getRandomMaleName() {
        return chooseRandomString(MALE_NAMES_LIST_FILENAME);
    }

    @Override
    public String getRandomSurname() {
        return chooseRandomString(SURNAMES_LIST_FILENAME);
    }

    @Override
    public String getRandomSecondName() {
        return chooseRandomString(SECOND_NAMES_ROOT_LIST_FILENAME);
    }


    private static String chooseRandomString(String filename) {
        String result = null;
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filename),Charset.forName("utf-8"));
            int randomString = randomNum(0,lines.size()-1);
            result = lines.get(randomString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
