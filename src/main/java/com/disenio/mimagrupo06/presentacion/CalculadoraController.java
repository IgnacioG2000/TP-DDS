package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public class CalculadoraController {

    @Autowired
    RepoOrganizacion repoOrganizacion;

@GetMapping("/calculadora/organizacion/hcAnual")
public ResponseEntity calcularHCTotalAnio(@RequestHeader("Authorization") String idSesion, @RequestBody String anio){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);

    double resultado = unaOrganizacion.calcularHuellaCarbonoTotalAnio(Integer.parseInt(anio));

    if (unaOrganizacion == null) {
        return ResponseEntity.status(404).build();
    }

    repoOrganizacion.save(unaOrganizacion);

    return ResponseEntity.status(200).body(resultado);
}

}
