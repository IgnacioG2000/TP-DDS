package com.disenio.mimagrupo06.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ExcelService {
//.//src//main//resources//files//
  //private String upload_folder = "/src/main/resources/files/";
  //src//main//java//com//disenio//mimagrupo06//excel_ETL//
  private String upload_folder = "./src/main/resources/files/";

  public void saveFile(MultipartFile file) throws IOException{
    if(!file.isEmpty()){
      byte[] bytes = file.getBytes();
      Path path = Paths.get(upload_folder + file.getOriginalFilename());
      Files.write(path, bytes);

    }

  }

  public String obtenerPath(MultipartFile file){
    return "./src/main/resources/files/" + file.getOriginalFilename();
  }

  public void deleteFile(MultipartFile file) throws IOException{
    Path path = Paths.get(upload_folder + file.getOriginalFilename());
    Files.deleteIfExists(path);
  }

}