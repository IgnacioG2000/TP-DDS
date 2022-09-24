package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.apiDistancia.Pais;
import com.disenio.mimagrupo06.domain.sector.PaisSector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPaisSector extends CrudRepository<PaisSector, Long> {
}
