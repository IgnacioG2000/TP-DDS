/*
* Acá esta toda la logica referida a modificar la pantalla de tramos
* */

const opcionesTramo = document.getElementById("tipoTramo")

const tramoNuevos = document.getElementById("tramoNuevo")
const tramoExistente = document.getElementById("tramoExistente")


// medios de transporte

const medioDeTransporte = document.getElementById("medioTransporte");

const servicioContratado = document.getElementById("servicioContratado")
const noMotorizado = document.getElementById("noMotorizado")
const transportePublico = document.getElementById("transportePublico")
const vehiculoParticular = document.getElementById("vehiculoParticular")


opcionesTramo.addEventListener('change', e=> {
    e.preventDefault();
    mostrarSegunRespuesta(opcionesTramo.value);
    })

medioDeTransporte.addEventListener('change', e=> {
    e.preventDefault();
    mostrarMedioTransporteSegunRespuesta(medioDeTransporte.value)
})

function mostrarMedioTransporteSegunRespuesta(medioTransporteSeleccionado) {
    console.log(medioTransporteSeleccionado)

    if(medioTransporteSeleccionado === "servicioContratado") {
        servicioContratado.classList.remove("hidden");
        noMotorizado.classList.add("hidden");
        transportePublico.classList.add("hidden");
        vehiculoParticular.classList.add("hidden");
    }else if (medioTransporteSeleccionado === "transporteNoMotorizado"){
        noMotorizado.classList.remove("hidden")
        transportePublico.classList.add("hidden");
        vehiculoParticular.classList.add("hidden");
        servicioContratado.classList.add("hidden")

    }else if (medioTransporteSeleccionado === "transportePublico"){
        transportePublico.classList.remove("hidden")
        vehiculoParticular.classList.add("hidden");
        noMotorizado.classList.add("hidden");
        servicioContratado.classList.add("hidden")
    }else {
        //vechiculo particular
        vehiculoParticular.classList.remove("hidden")
        noMotorizado.classList.add("hidden");
        servicioContratado.classList.add("hidden")
        transportePublico.classList.add("hidden");
    }

}

function mostrarSegunRespuesta(opcion) {

    console.log(opcion)
    if(opcion === "nuevo") {
        tramoNuevos.classList.remove("hidden");
    }else {
        tramoExistente.classList.remove("hidden");
        tramoNuevos.classList.add("hidden");

    }
}




