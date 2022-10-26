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

import java.util.ArrayList;
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

       //ca.cargarFE();
       da.cargarDatos();

        TipoActividad gasNatural = new TipoActividad("Gas Natural", "m3",10);
        TipoActividad dieselGasoil = new TipoActividad("DieselGasoil", "lt",7);
        TipoActividad kerosene = new TipoActividad("Kerosene", "lt",5);
        TipoActividad fuelOil= new TipoActividad("Fuel Oil", "lt",8);
        TipoActividad nafta = new TipoActividad("Nafta", "lt",9);
        TipoActividad carbon = new TipoActividad("Carbon", "kg",10);
        TipoActividad carbonDeLenia = new TipoActividad("Carbon de Lenia", "kg",5);
        TipoActividad lenia = new TipoActividad("Lenia", "kg",8);
        TipoActividad combustibleGasoil = new TipoActividad("Combustible Gasoil", "lt",7);
        TipoActividad combustibleGNC = new TipoActividad("Combustible GNC", "lt",8);
        TipoActividad combustibleNafta = new TipoActividad("Combustible Nafta", "lt",7);
        TipoActividad electricidad = new TipoActividad("Electricidad", "Kwh",10);
        TipoActividad distMediaRecorrida = new TipoActividad("Distancia Media Recorrida", "km",6);
        TipoActividad pesoTotalTransportado = new TipoActividad("Peso Total Transportado", "kg",4);
        TipoActividad materiaPrima = new TipoActividad("Materia Prima", " ",1);
        TipoActividad camionUtilitario = new TipoActividad("Medio de Transporte: Camion utilitario", " ",1);
        TipoActividad camionCarga = new TipoActividad("Medio de Transporte: Camion de carga", " ",1);


        System.out.println("No está nula, va a arrancar a guardar");
        List<TipoActividad> tiposActividad = new ArrayList<>();
        tiposActividad.addAll(Arrays.asList(gasNatural, dieselGasoil, kerosene, fuelOil, nafta, carbon, carbonDeLenia, lenia, combustibleGasoil, combustibleGNC, combustibleNafta,
              electricidad, distMediaRecorrida, pesoTotalTransportado, materiaPrima, camionUtilitario, camionCarga));

          tiposActividad.forEach(da->ta.save(da));
      }



    }
}
