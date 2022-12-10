package com.disenio.mimagrupo06.seguridad.roles;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@DiscriminatorValue("4")
public class UsuarioOrganizacion extends Usuario{

    public UsuarioOrganizacion() {

    }

    public UsuarioOrganizacion(String usuario, String contrasenia) throws NoSuchAlgorithmException {
        super(usuario, contrasenia);
    }


}
