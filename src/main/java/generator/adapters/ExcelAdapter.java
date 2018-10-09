package generator.adapters;

import generator.utils.DataGenerator;
import generator.components.Table;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelAdapter implements TableAdapter{

    private Logger log = Logger.getLogger(ExcelAdapter.class);

    private Table table;
    private Workbook workbook;
    private Sheet currentSheet;
    private CellStyle headerCellStyle;

    public ExcelAdapter() {}

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public void initTable() {
        workbook = new XSSFWorkbook();
        currentSheet = workbook.createSheet(table.getSheetName());
    }

    @Override
    public void setHeaders() {
        setStyles();
        Row headerRow = currentSheet.createRow(0);

        for (int i = 0; i<table.getHeaders().size(); i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue((String)table.getHeaders().get(i));
            cell.setCellStyle(headerCellStyle);
        }
    }

    @Override
    public void fillTableWithData() {
        Object[][] data = table.getPreparedData();
        int rowNum = 1;
        for(Object[] objects : data){
            Row tableRow = currentSheet.createRow(rowNum++);
            for (int col=0; col < objects.length; col++){
                Object param = objects[col];
                if(param.getClass().getName().contains("String")){
                    tableRow
                            .createCell(col)
                            .setCellValue((String)param);
                } else {
                    tableRow
                            .createCell(col)
                            .setCellValue((int)param);
                }
                currentSheet.autoSizeColumn(col);
            }
        }
    }

    @Override
    public void saveDocument() {
        String filename =  DataGenerator.filenameGenerator(table.getObjectClass(),"xlsx");
        File file = new File(filename);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(file.exists()) log.info("Файл создан. Путь:"+file.getAbsolutePath());
    }

    private void setStyles(){
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
