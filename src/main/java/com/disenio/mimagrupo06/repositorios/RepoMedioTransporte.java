package com.disenio.mimagrupo06.repositorios;



import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RepoMedioTransporte extends CrudRepository<MedioDeTransporte, Long> {
}
