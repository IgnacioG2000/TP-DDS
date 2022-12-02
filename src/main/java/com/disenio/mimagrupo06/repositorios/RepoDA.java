package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.excel_ETL.DatoDeLaActividad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoDA extends CrudRepository<DatoDeLaActividad, Long> {

}
