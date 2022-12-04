package com.disenio.mimagrupo06.repositorios;


import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensual;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoValorHCMensual extends CrudRepository<ValorHCMensual, Long> {

}
