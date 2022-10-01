package com.disenio.mimagrupo06;

import com.disenio.mimagrupo06.apiDistancia.DatosApi;
import com.disenio.mimagrupo06.apiDistancia.Pais;
import com.disenio.mimagrupo06.apiDistancia.ServicioApiDistancia;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.TipoActividad;
import com.disenio.mimagrupo06.domain.sector.PaisSector;
import com.disenio.mimagrupo06.repositorios.RepoPaisSector;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private RepoTA ta;

    @Autowired
    private CalculadoraHCActividad ca;

    @Autowired
    private DatosApi da;

    @Override
    public void run(String... args) throws Exception {

      if(ta.count() == 0) {

       ca.cargarFE();
       da.cargarDatos();

      }



    }
}
