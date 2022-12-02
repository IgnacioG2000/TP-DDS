package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Hogar;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import org.springframework.data.repository.CrudRepository;

public interface RepoHogar  extends CrudRepository<Hogar, Long> {
}
