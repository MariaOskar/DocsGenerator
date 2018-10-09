package generator;

import generator.adapters.ExcelAdapter;
import generator.utils.DataGenerator;
import generator.adapters.PDFAdapter;
import generator.models.Person;
import generator.components.Table;


public class Application {

    public static void main(String[] args) {

        Table<Person> personTable = new Table<>(Person.class, DataGenerator.generateSomePeople());

        personTable
                .setAdapter(new ExcelAdapter())
                .create();

        personTable
                .setAdapter(new PDFAdapter())
                .create();
    }
}
