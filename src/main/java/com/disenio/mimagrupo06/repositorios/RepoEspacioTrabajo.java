package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import org.springframework.data.repository.CrudRepository;

public interface RepoEspacioTrabajo  extends CrudRepository<EspacioDeTrabajo, Long> {
}
