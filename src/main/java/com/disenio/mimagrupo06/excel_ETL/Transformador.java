package com.disenio.mimagrupo06.excel_ETL;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.TipoActividad;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.domain.organizacion.OrganizacionService;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

@Service
public class Transformador {

  private Collection<DatoDeLaActividad> datoDeLaActividad;
  private RepoTA ta;

/*
  @Bean
  public Transformador transformador() {
    Transformador transformador = new Transformador();
    return transformador;
  }
*/
  public void cargarDatos(Organizacion org, String pathParcial)
  {

    try {

      FileResourceUtils app = new FileResourceUtils();

      //conseguimos el path del proyecto
      Path path = Paths.get("");

      String directoryName = path.toAbsolutePath().toString();

      //conseguimos abrir el excel con el path del mismo dentro del proyecto
      //FileInputStream file = new FileInputStream(new File( directoryName + pathParcial));
      InputStream file = app.getFileFromResourceAsStream(pathParcial);

      //Create Workbook instance holding reference to .xls file
      HSSFWorkbook workbook = new HSSFWorkbook(file);

      //Get first/desired sheet from the workbook
      HSSFSheet sheet = workbook.getSheetAt(0);

      //Iterate through each rows one by one
      Iterator<Row> rowIterator = sheet.iterator();
      //nos salteamos las primeras dos lineas del excel

      datoDeLaActividad = new ArrayList<>();
      System.out.print(datoDeLaActividad.size());
      System.out.println("");

      rowIterator.next();
      rowIterator.next();

      while (rowIterator.hasNext())
      {
        DatoDeLaActividad nuevoDato = new DatoDeLaActividad();
        Row row = rowIterator.next();
        nuevoDato.setActividad(row.getCell(0).getStringCellValue());
        String tipoNombre = row.getCell(1).getStringCellValue();
        TipoActividad tipoActividad = ta.findByNombre(tipoNombre).get();
        nuevoDato.setTipoActividad(tipoActividad);
        Consumo consumo = new Consumo();
        consumo.setValor(row.getCell(2).getNumericCellValue());
        consumo.setPeriocidad(row.getCell(3).getStringCellValue());
        nuevoDato.setConsumo(consumo);
        //nuevoDato.setPeriodoDeImputacion(NumberToTextConverter.toText(row.getCell(4).getNumericCellValue()));
        nuevoDato.setPeriodoDeImputacion(row.getCell(4).getStringCellValue());
        //nuevoDato.setPeriodoDeImputacion(row.getCell(4).setCellType(Cell.CELL_TYPE_STRING));

        //Agrego el dato a la lista
        datoDeLaActividad.add(nuevoDato);
        //Verifico los datos que va a cargar // Se borra dsp
        System.out.println(nuevoDato.getActividad());
        System.out.println(nuevoDato.getTipoDeConsumo());
        System.out.println(nuevoDato.getConsumo().getValor());
        System.out.println(nuevoDato.getConsumo().getPeriocidad());
        System.out.println(nuevoDato.getPeriodoDeImputacion());

        System.out.println("");
      }


      file.close();

      org.setDatosDeLaActividad(datoDeLaActividad);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  public Collection<DatoDeLaActividad> getDatosDeLaActividad() {
    return datoDeLaActividad;
  }

  public RepoTA getTa() {
    return ta;
  }

  public void setTa(RepoTA ta) {
    this.ta = ta;
  }
}


