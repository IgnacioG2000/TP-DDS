package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.presentacion.dto.*;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class TrayectosController {
    @Autowired
    RepoArea repoArea;
    @Autowired
    RepoPersona repoPersona;
    @Autowired
    RepoTrayecto repoTrayecto;
    @Autowired
    RepoTramos repoTramos;
    @Autowired
    RepoEspacio repoEspacio;
    @Autowired
    RepoMiembro repoMiembro;
    @Autowired
    RepoMedioTransporte repoMedioTransporte;

    private final Handlebars handlebars = new Handlebars();
    @GetMapping("/trayectos")
    public ResponseEntity obtenerTrayectos(@RequestHeader("Authorization") String idSesion){
        Iterable<Trayecto> listaTrayectos = repoTrayecto.findAll();
        return ResponseEntity.status(201).body(listaTrayectos);
    }

    @GetMapping("/registrarTrayecto")
    public ResponseEntity<String> registrarTrayecto() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/eleccionCreacionTrayecto");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    @GetMapping("/registrarTrayectoNuevo")
    public ResponseEntity<String> registrarTrayectoNuevo() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/registrarTrayectoNuevo");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    @GetMapping("/registrarTrayectoExistente")
    public ResponseEntity<String> registrarTrayectoExistente() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/registrarTrayectoExistente");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }


    @PostMapping("/registrarTrayectoExistente")
    public void recibirTrayectoExistente(@RequestBody TrayectoExistenteDTO trayectoExistenteDTO) throws IOException {
        Miembro miembroSesion = this.encontrarMiembro(trayectoExistenteDTO.getIdSesion(), trayectoExistenteDTO.getArea());
        System.out.println("Estoy recibiendo un trayecto existente");
        Trayecto trayecto = repoTrayecto.findById(trayectoExistenteDTO.getIdTrayecto()).get();
        trayecto.getTramos().forEach(tramo -> tramo.agregarMiembro(miembroSesion));
        repoTrayecto.save(trayecto);
    }

    @PostMapping("/registrarTrayectoNuevo")
    public void recibirTrayectoNuevo(@RequestBody TrayectoNuevoDTO trayectoNuevoDTO) throws IOException {

        Miembro miembroSesion = this.encontrarMiembro(trayectoNuevoDTO.getIdSesion(), trayectoNuevoDTO.getNombreArea());

        Espacio espacioPartida = this.getEspacio(trayectoNuevoDTO.getEspacioPartida());
        Espacio espacioLlegada = this.getEspacio(trayectoNuevoDTO.getEspacioLlegada());

        List<Tramo> listaTramosTrayectoNuevo = new ArrayList<Tramo>();
        trayectoNuevoDTO.getTramos().stream().forEach(tramoDTO -> {
            try {
                this.agregarTramos(tramoDTO, listaTramosTrayectoNuevo, miembroSesion);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Trayecto trayectoNuevo = new Trayecto(espacioPartida, espacioLlegada, listaTramosTrayectoNuevo, trayectoNuevoDTO.getFechaInicio(), trayectoNuevoDTO.getDiasUtilizados());
        repoTrayecto.save(trayectoNuevo);

        miembroSesion.getArea().agregarVinculacion(trayectoNuevo);
        repoArea.save(miembroSesion.getArea());

    }


    private void agregarTramos(TramoDTO tramoDTO, List<Tramo> listaTramos, Miembro miembroSesion) throws IOException {
        if(tramoDTO.getId() == null) {

            Espacio espacioPartida = this.getEspacio(tramoDTO.getPartida());
            Espacio espacioLlegada = this.getEspacio(tramoDTO.getLlegada());

            MedioDeTransporte medioDeTransporte = this.getMedioDeTransporte(tramoDTO.getTransporte());

            List<Miembro> miembros =new ArrayList<Miembro>();
            miembros.add(miembroSesion);


            Tramo tramoNuevo = new Tramo(espacioPartida, espacioLlegada, medioDeTransporte, miembros);
            repoTramos.save(tramoNuevo);

            listaTramos.add(tramoNuevo);

        }else{
            Tramo tramo =repoTramos.findById(tramoDTO.getId()).get();
            listaTramos.add(tramo);
        }
    }

    private Espacio getEspacio(EspacioDTO espacioDTO){

        Espacio espacio = null;

        if(espacioDTO.getId() == null){
            switch(espacioDTO.getClase()){
                case "hogar":{
                    espacio = new Hogar(espacioDTO.getLatitud(), espacioDTO.getLongitud(), espacioDTO.getProvincia(), espacioDTO.getMunicipio(), espacioDTO.getLocalidad(),
                            espacioDTO.getDireccion(), espacioDTO.getNumero(), espacioDTO.getCodigoPostal(), espacioDTO.getPisoDepartamento(), espacioDTO.getDepartamento(),
                            espacioDTO.getTipoDeHogar());
                    break;
                }
                case "trabajo":{
                    espacio = new EspacioDeTrabajo(espacioDTO.getLatitud(), espacioDTO.getLongitud(), espacioDTO.getProvincia(), espacioDTO.getMunicipio(), espacioDTO.getLocalidad(),
                            espacioDTO.getDireccion(), espacioDTO.getNumero(), espacioDTO.getCodigoPostal(), espacioDTO.getPisoDepartamento(), espacioDTO.getUnidad());
                    break;
                }
                case "parada":{
                    espacio = new Parada(espacioDTO.getLatitud(), espacioDTO.getLongitud(), espacioDTO.getProvincia(), espacioDTO.getMunicipio(), espacioDTO.getLocalidad(),
                            espacioDTO.getDireccion(), espacioDTO.getNumero(), espacioDTO.getCodigoPostal());
                    break;
                }
            }
            repoEspacio.save(espacio);

        }else{
            espacio = repoEspacio.findById(espacioDTO.getId()).get();
        }

        return espacio;
    }

    private MedioDeTransporte getMedioDeTransporte(MedioDeTransporteDTO medioDeTransporteDTO) throws IOException {

        MedioDeTransporte medioDeTransporte = null;

        if(medioDeTransporteDTO.getId() == null){
            switch(medioDeTransporteDTO.getClaseAInicializar()){
                case "servicioContratado":{
                    medioDeTransporte = new ServicioContratado(medioDeTransporteDTO.getTipoServicioContratado());
                    break;
                }
                case "transporteNoMotorizado":{
                    medioDeTransporte = new TransporteNoMotorizado(medioDeTransporteDTO.getTipoNoMotorizado());
                    break;
                }
                case "transportePublico":{
                    medioDeTransporte = new TransportePublico(medioDeTransporteDTO.getTipoTransporte(),medioDeTransporteDTO.getNombre());
                    break;
                }
                case "vehiculoParticular":{
                    medioDeTransporte = new VehiculoParticular(medioDeTransporteDTO.getTipoVehiculoParticular(), medioDeTransporteDTO.getTipoCombustible());
                    break;
                }
            }
            repoMedioTransporte.save(medioDeTransporte);

        }else {
            medioDeTransporte = repoMedioTransporte.findById(medioDeTransporteDTO.getId()).get();
        }

        return medioDeTransporte;
    }


    public Miembro encontrarMiembro(String idSesion, String nombreArea) {

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
        Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);
        List<Miembro> miembrosDeSesion = repoMiembro.findAllByPersona(personaSesion);

        List<Miembro> miembrosDeSesionConArea = miembrosDeSesion.stream()
            .filter(miembro -> mismaArea(miembro,nombreArea))
            .collect(Collectors.toList());

        return  miembrosDeSesionConArea.get(0);
    }

    private boolean mismaArea(Miembro miembro, String nombreArea){
        return miembro.getArea().getNombre().equals(nombreArea);
    }
}
