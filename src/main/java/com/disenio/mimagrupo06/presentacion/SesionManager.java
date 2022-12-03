package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.miembro.Miembro;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SesionManager {
/*
    @Autowired
    RepoPersona repoPersona;
    @Autowired
    RepoMiembro repoMiembro;
*/

    private static SesionManager instancia;

    private Map<String, Map<String, Object>> sesiones;

    private SesionManager() {
        this.sesiones = new HashMap<>();
    }

    public static SesionManager get() {
        if (instancia == null) {
            instancia = new SesionManager();
        }
        return instancia;
    }

    public String crearSesion() {
        return this.crearSesion(new HashMap<>());
    }

    public String crearSesion(String clave, Object valor) {
        HashMap<String, Object> atributo = new HashMap<>();
        atributo.put(clave, valor);
        return this.crearSesion(atributo);
    }

    public String crearSesion(Map<String, Object> atributos) {
        String id = UUID.randomUUID().toString();
        this.sesiones.put(id, atributos);
        return id;
    }

    public Boolean estaLogeado(String idSesion) {
        return this.sesiones.containsKey(idSesion);
    }

    public Map<String, Object> obtenerAtributos(String id) {
        return this.sesiones.get(id);
    }

    public void agregarAtributo(String id, String clave, Object valor) {
        Map<String, Object> atributos = this.sesiones.get(id);
        atributos.put(clave, valor);
    }

    public void agregarAtributos(String id, Map<String, Object> nuevosAtributos) {
        Map<String, Object> atributos = this.sesiones.get(id);
        atributos.putAll(nuevosAtributos);
    }

    public Map<String, Object> eliminar(String id) {
        //esto no elimina la cookie del frontend
        return this.sesiones.remove(id);
    }
/*
    public Miembro encontrarMiembro(String idSesion, String nombreArea) {

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
        Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
        Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);
        List<Miembro> miembrosDeSesion = repoMiembro.findAllByPersona(personaSesion);
        System.out.println("cantidad de miembros asociados al usuario ingresado: " + miembrosDeSesion.size());
        System.out.println("area del miembro del usuario: " + miembrosDeSesion.get(0).getArea().getNombre());
        System.out.println("area del miembro del usuario: " + miembrosDeSesion.get(1).getArea().getNombre());
        System.out.println("area del miembro que esta en sistema: " + nombreArea);

        List<Miembro> miembrosDeSesionConArea = miembrosDeSesion.stream()
                .filter(miembro -> mismaArea(miembro,nombreArea))
                .collect(Collectors.toList());
        System.out.println("cantidad de miembros asociados al usuario ingresado con su area: " + miembrosDeSesionConArea.size());

        System.out.println("El area del miembro buscado es "+ nombreArea);
       return  miembrosDeSesionConArea.get(0);//aca tendria q usarse miembrosDeSesionConArea pero la comparacion con string no esta funcionando
    }
*/
    private boolean mismaArea(Miembro miembro, String nombreArea){
        return miembro.getArea().getNombre().equals(nombreArea);
    }
}
