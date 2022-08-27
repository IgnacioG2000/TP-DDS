package excel_ETL;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Transformador {

  private List<DatosDeLaActividad> datosDeLaActividad;

  public void cargarDatos(String pathParcial)
  {
    try {
      //conseguimos el path del proyecto
      Path path = Paths.get("");
      String directoryName = path.toAbsolutePath().toString();

      //conseguimos abrir el excel con el path del mismo dentro del proyecto
      FileInputStream file = new FileInputStream(new File( directoryName + pathParcial));

      //Create Workbook instance holding reference to .xls file
      HSSFWorkbook workbook = new HSSFWorkbook(file);

      //Get first/desired sheet from the workbook
      HSSFSheet sheet = workbook.getSheetAt(0);

      //Iterate through each rows one by one
      Iterator<Row> rowIterator = sheet.iterator();
      //nos saleamos las prieras dos lineas del excel

      datosDeLaActividad = new ArrayList<>();
      System.out.print(datosDeLaActividad.size());
      System.out.println("");

      rowIterator.next();
      rowIterator.next();

      while (rowIterator.hasNext())
      {
        DatosDeLaActividad nuevoDato = new DatosDeLaActividad();
        Row row = rowIterator.next();
        nuevoDato.setActividad(row.getCell(0).getStringCellValue());
        nuevoDato.setTipoDeConsumo(row.getCell(1).getStringCellValue());
        Consumo consumo = new Consumo();
        consumo.setValor(row.getCell(2).getNumericCellValue());
        consumo.setPeriocidad(row.getCell(3).getStringCellValue());
        nuevoDato.setConsumo(consumo);
        nuevoDato.setPeriodoDeImputacion(row.getCell(4).getStringCellValue());

        //Agrego el dato a la lista
        datosDeLaActividad.add(nuevoDato);
        //Verifico los datos que va a cargar // Se borra dsp
        System.out.println(nuevoDato.getActividad());
        System.out.println(nuevoDato.getTipoDeConsumo());
        System.out.println(nuevoDato.getConsumo().getValor());
        System.out.println(nuevoDato.getConsumo().getPeriocidad());
        System.out.println(nuevoDato.getPeriodoDeImputacion());

        System.out.println("");
      }
      System.out.print(datosDeLaActividad.size());

      file.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  public List<DatosDeLaActividad> getDatosDeLaActividad() {
    return datosDeLaActividad;
  }


}


