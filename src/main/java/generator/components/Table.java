package generator.components;

import generator.adapters.TableAdapter;
import generator.utils.Preparable;
import generator.utils.Title;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Table<T extends Preparable> {

    private Class objectClass;
    private List<T> data;
    private List<String> headers;
    private String sheetName;
    private TableAdapter adapter;


    public Table(Class objClass, List<T> data) {
        this.objectClass = objClass;
        this.data = data;
        this.sheetName = ((Title)objClass.getAnnotation(Title.class)).value();
        this.headers = new ArrayList<>();
        for(Field field : objectClass.getDeclaredFields()){
            this.headers.add(field.getAnnotation(Title.class).value());
        }
    }

    public Class getObjectClass() {
        return objectClass;
    }

    public List<T> getData() {
        return data;
    }

    public String getSheetName() {
        return sheetName;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public TableAdapter getAdapter() {
        return adapter;
    }

    public Table<T> setAdapter(TableAdapter adapter) {
        adapter.setTable(this);
        this.adapter = adapter;
        return this;
    }

     public Table<T> initTable(){
        adapter.initTable();
        return this;
     }
     public Table<T> setHeaders(){
         adapter.setHeaders();
         return this;
     }
     public Table<T> fillTableWithData(){
         adapter.fillTableWithData();
         return this;
     }
     public Table<T> saveDocument(){
         adapter.saveDocument();
         return this;
     }

     public void create(){
        this.initTable()
            .setHeaders()
            .fillTableWithData()
            .saveDocument();
     }

    public Object[][] getPreparedData(){
        Object[][] preparedData = new Object[getData().size()][];
        for (int i=0; i < getData().size(); i++){
            preparedData[i] =  getData().get(i).prepareData();
        }
        return preparedData;
    }
}
