package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.excel_ETL.Consumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoConsumo extends CrudRepository<Consumo, Long> {

}
