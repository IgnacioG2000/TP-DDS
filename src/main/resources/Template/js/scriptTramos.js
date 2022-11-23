/*
* Acá esta toda la logica referida a modificar la pantalla de tramos
* */

const opcionesTramo = document.getElementById("tipoTramo")

const tramoNuevos = document.getElementById("tramoNuevo")
const tramoExistente = document.getElementById("tramoExistente")

const opcionesTramoPartida = document.getElementById("tipoPartidaTramo")
const opcionesTramoLlegada =  document.getElementById("tipoLlegadaTramo")

const espacioPartidaTramoNuevo = document.getElementById("espacioNuevoPartidaTramo")
const espacioPartidaTramoExistentes = document.getElementById("espacioExistentesPartidaTramo")

const espacioLlegadaTramoNuevo = document.getElementById("espacioNuevoLlegadaTramo")
const espacioLlegadaTramoExistentes = document.getElementById("espacioExistentesLlegadaTramo")


// medios de transporte

const medioDeTransporte = document.getElementById("medioTransporte");

const servicioContratado = document.getElementById("divServicioContratado")
const noMotorizado = document.getElementById("divNoMotorizado")
const transportePublico = document.getElementById("divTransportePublico")
const vehiculoParticular = document.getElementById("divVehiculoParticular")


opcionesTramo.addEventListener('change', e=> {
    e.preventDefault();
    mostrarSegunRespuesta(opcionesTramo.value);
    })

medioDeTransporte.addEventListener('change', e=> {
    e.preventDefault();
    mostrarMedioTransporteSegunRespuesta(medioDeTransporte.value)
})

opcionesTramoPartida.addEventListener('change', e=> {
    e.preventDefault();
   mostrarSegunRespuestaEspacioPartidaTramo(opcionesTramoPartida.value);
})

opcionesTramoLlegada.addEventListener('change', e=> {
  e.preventDefault()
   mostrarSegunRespuestaEspacioLlegadaTramo(opcionesTramoLlegada.value)
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
        console.log("nuevito")
    }else {
        console.log("existente")
        tramoExistente.classList.remove("hidden");
        tramoNuevos.classList.add("hidden");

    }
}

function mostrarSegunRespuestaEspacioPartidaTramo(valor) {

    console.log("estoy en mostrar segun rta espacio llegada:" + valor)
    if (valor === "nuevo") {

        espacioPartidaTramoNuevo.classList.remove("hidden");
        espacioPartidaTramoExistentes.classList.add("hidden");
        //oculto el existente y muestro el nuevo


    }else {
        espacioPartidaTramoNuevo.classList.add("hidden");
        espacioPartidaTramoExistentes.classList.remove("hidden");
        //oculto el nuevo y muestro el existete
    }
}


function mostrarSegunRespuestaEspacioLlegadaTramo(valor) {

    console.log("estoy en mostrar segun rta espacio llegada:" + valor)
    if (valor === "nuevo") {

        espacioLlegadaTramoNuevo.classList.remove("hidden");
        espacioLlegadaTramoExistentes.classList.add("hidden");
        //oculto el existente y muestro el nuevo


    }else {
        espacioLlegadaTramoNuevo.classList.add("hidden");
        espacioLlegadaTramoExistentes.classList.remove("hidden");
        //oculto el nuevo y muestro el existete
    }
}


