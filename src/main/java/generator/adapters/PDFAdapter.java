package generator.adapters;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import generator.utils.DataGenerator;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;

public class PDFAdapter implements TableAdapter{

    private Logger log = Logger.getLogger(PDFAdapter.class);

    private generator.components.Table table;

    private static final String ARIAL = "src\\main\\resources\\fonts\\9041.ttf";
    private Document doc;
    private PdfDocument pdfDoc;
    private Table pdfTable;
    private PdfFont font;

    public PDFAdapter() {
        try {
            font = getFont();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTable(generator.components.Table table) {
        this.table = table;
    }

    public void initTable(){
        pdfTable = new Table(table.getHeaders().size());
    }

    public void fillTableWithData(){
        for(Object[] object : table.getPreparedData()){
            for (Object element: object){
                Paragraph p = new Paragraph().add(String.valueOf(element));
                Cell cell = new Cell();
                setDataStyles(cell);
                pdfTable.addCell(cell.add(p));
            }
        }
    }

    public void setHeaders (){
        for(Object header: table.getHeaders()){
            Paragraph p = new Paragraph().add(new Text((String)header));
            Cell cell = new Cell();
            setHeaderStyles(cell);
            pdfTable.addCell(cell.add(p));
        }
    }

    public void saveDocument(){
        String filename = DataGenerator.filenameGenerator(table.getObjectClass(),"pdf");
        File file = new File(filename);
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        try {
            pdfDoc = new PdfDocument(new PdfWriter(filename));
            doc = new Document(pdfDoc);
            setPageParameters();
            doc.add(pdfTable);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(file.exists())
            log.info("Файл создан. Путь:"+file.getAbsolutePath());
    }

    private PdfFont getFont() throws IOException {
        return PdfFontFactory.createFont(ARIAL, PdfEncodings.IDENTITY_H);
    }

    private void setPageParameters(){
        PageSize pageSize = pdfDoc.getDefaultPageSize();
        pdfDoc.setDefaultPageSize(new PageSize(pageSize.getHeight(),pageSize.getWidth()));

    }


    private void setHeaderStyles(Cell cell){
        cell.setFont(font)
                .setFontSize(9)
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5)
                .setMargin(2);
    }

    private void setDataStyles(Cell cell){
        cell.setFont(font)
                .setFontSize(9)
                .setTextAlignment(TextAlignment.CENTER);
    }
}


