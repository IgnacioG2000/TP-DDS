package com.disenio.mimagrupo06;

import com.disenio.mimagrupo06.apiDistancia.DatosApi;
import com.disenio.mimagrupo06.apiDistancia.Pais;
import com.disenio.mimagrupo06.apiDistancia.ServicioApiDistancia;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.TipoActividad;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.domain.miembro.TipoDocumento;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Clasificacion;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.domain.organizacion.TipoDeOrganizacion;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioComun;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private RepoTA ta;

    @Autowired
    private DatosApi da;

    @Autowired
    private RepoUsuario ru;

    @Autowired
    private RepoPersona rp;

    @Autowired
    private RepoMiembro rm;

    @Autowired
    private RepoEspacio re;

    @Autowired
    private RepoArea ra;

    @Autowired
    private RepoOrganizacion ro;

    @Autowired
    private RepoMedioTransporte rmt;

    @Autowired
    private RepoTramos rt;

    @Override
    public void run(String... args) throws Exception {

      if(ta.count() == 0) {

       //ca.cargarFE();
       da.cargarDatos();

        Espacio espacioOrigen = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "maipu", "100", 1992);
        re.save(espacioOrigen);
        Espacio espacioDestino = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992);
        re.save(espacioDestino);
        MedioDeTransporte medioDeTransporte1 = new TransportePublico(TipoTransportePublico.TREN, "Tren Roca" );
        rmt.save(medioDeTransporte1);
        MedioDeTransporte medioDeTransporte2 = new VehiculoParticular(TipoVehiculo.AUTO,TipoCombustible.NAFTA);
        rmt.save(medioDeTransporte2);
        UsuarioComun usuarioGuido = new UsuarioComun("Guido2000", "contraCOntraKCRF123");
        ru.save(usuarioGuido);

        UsuarioOrganizacion usuarioGuido2 = new UsuarioOrganizacion("Guido2001@gmail.com", "contraCOntraKCRF123");
        ru.save(usuarioGuido2);

        Hogar hogarGuido = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, "unbarrio", 3, "Hola", TipoDeHogar.CASA);
        re.save(hogarGuido);
        Persona personaGuido = new Persona("Guido", "Serco", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioGuido);
        rp.save(personaGuido);
        Miembro miembroGuido = new Miembro(personaGuido);
        rm.save(miembroGuido);
        Miembro miembroGuido2 = new Miembro(personaGuido);
        rm.save(miembroGuido2);
        Hogar hogarTaylor = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, "unbarrio", 3, "Hola", TipoDeHogar.CASA);
        re.save(hogarTaylor);
        UsuarioComun usuarioTaylor = new UsuarioComun("Taylor1234", "djf8ree245");
        ru.save(usuarioTaylor);
        Persona personaTaylor = new Persona("Taylor", "Swift", TipoDocumento.DNI, "367789999", hogarTaylor, usuarioTaylor);
        rp.save(personaTaylor);
        Miembro miembroTaylor = new Miembro(personaTaylor);
        rm.save(miembroTaylor);
        UsuarioComun usuarioJake = new UsuarioComun("Jake_The_Taylor_Hater", "Taylor_hater_4_life");
        ru.save(usuarioJake);
        Persona personaJake = new Persona("Jake", "Gyllenhaal", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioJake);
        rp.save(personaJake);
        Miembro miembroJake = new Miembro(personaJake);
        rm.save(miembroJake);
        Tramo tramo1 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte1, Arrays.asList(miembroGuido));
        rt.save(tramo1);
        Tramo tramo2 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte2, Arrays.asList(miembroTaylor,miembroJake));
        rt.save(tramo2);
        Tramo tramo3 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte2, Arrays.asList(miembroTaylor));
        rt.save(tramo3);

        //UsuarioComun usuarioComun = new UsuarioComun("hola", "ConTra Muy Bu3na");
        //ru.save(usuarioComun);


        EspacioDeTrabajo espacioTrabajoArea = new EspacioDeTrabajo(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992,2, "A");
        re.save(espacioTrabajoArea);
        Area area = new Area("Area1", Arrays.asList(miembroGuido), espacioTrabajoArea);
        Area area2 = new Area("Area2", Arrays.asList(miembroGuido2), espacioTrabajoArea);
        Area area4Ever21 = new Area("Area4Ever21", Arrays.asList(miembroTaylor,miembroJake), espacioTrabajoArea);
        ra.save(area);
        ra.save(area2);
        ra.save(area4Ever21);

        Organizacion organizacion = new Organizacion("Nueva Seguro", TipoDeOrganizacion.EMPRESA, Arrays.asList(area4Ever21),
                 Clasificacion.MINISTERIO);
        organizacion.setUsuarioOrganizacion(usuarioGuido2);
        ro.save(organizacion);

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
/* JSON para controller de Miembro en agregar trayecto
{
    "nombreArea":"Area1",
    "latitudPartida": 100,
    "longitudPartida":100,
    "provinciaPartida": "aaa",
    "municipioPartida": "bbb",
    "localidadPartida": "ccc",
    "direccionPartida": "ddd",
    "numeroPartida":"555",
    "codigoPostalPartida":"1414",
    "latitudLlegada": 500,
    "longitudLlegada": 800,
    "provinciaLlegada":"eee",
    "municipioLlegada": "fff",
    "localidadLlegada":"ggg",
    "direccionLlegada":"hhh",
    "numeroLlegada":"5",
    "codigoPostalLlegada": 147,
    "tramos":null,
    "fechaInicio": "2022-04-19",
    "fechaFin": "2022-10-19",
    "diasUtilizados": 4
    }
*/