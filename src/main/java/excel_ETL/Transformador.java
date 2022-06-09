package excel_ETL;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class Transformador {
  public static void main(String[] args)
  {
    try
    {
      //conseguimos el path del proyecto
      Path path = Paths.get("");
      String directoryName = path.toAbsolutePath().toString();

      //conseguimos abrir el excel con el path del mismo dentro del proyecto
      FileInputStream file = new FileInputStream(new File( directoryName + "\\src\\main\\java\\excel_ETL\\excelTesteo.xls"));

      //Create Workbook instance holding reference to .xls file
      HSSFWorkbook workbook = new HSSFWorkbook(file);

      //Get first/desired sheet from the workbook
      HSSFSheet sheet = workbook.getSheetAt(0);

      //Iterate through each rows one by one
      Iterator<Row> rowIterator = sheet.iterator();
      //nos saleamoslas prieras dos lineas del excel
      rowIterator.next();
      rowIterator.next();

      while (rowIterator.hasNext())
      {
        Row row = rowIterator.next();
        //For each row, iterate through all the columns
        Iterator<Cell> cellIterator = row.cellIterator();

        //TODO ingresar datos en orden a las clases

        //Cell cell = cellIterator.next();

        while (cellIterator.hasNext())
        {
          Cell cell = cellIterator.next();
          //Check the cell type and format accordingly
          switch (cell.getCellType())
          {
            case NUMERIC:
              System.out.print(cell.getNumericCellValue() + " ");
              break;
            case STRING:
              System.out.print(cell.getStringCellValue() + " ");
              break;
          }
        }
        System.out.println("");
      }
      file.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}


