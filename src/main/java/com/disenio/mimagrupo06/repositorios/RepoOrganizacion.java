package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface RepoOrganizacion extends CrudRepository<Organizacion, Long> {
  //List<Organizacion> findAByUsuario();
  List<Organizacion> findAll();
  //List<Organizacion> findAll();
    Organizacion findByUsuarioOrganizacion(UsuarioOrganizacion usuarioOrganizacion);
}
