package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.sector.ProvinciaSector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProvinciaSector extends CrudRepository<ProvinciaSector, Long> {
    ProvinciaSector findByProvinciaCodigo(Long provinciaCodigo);
}