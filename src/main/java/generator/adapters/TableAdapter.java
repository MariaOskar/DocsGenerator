package generator.adapters;

import generator.components.Table;

public interface TableAdapter {
    void initTable();
    void setHeaders();
    void fillTableWithData();
    void saveDocument();
    void setTable(Table table);
}
