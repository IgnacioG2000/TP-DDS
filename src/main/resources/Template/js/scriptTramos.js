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

const tramosCargados = document.getElementById("tramosCargados");

const botonAgregarNuevoTramo = document.getElementById("agregarMasTramos");

const espacioSeleccionadoPartidaTramo = document.getElementById("tipoEspacioPartidaTramo")
const espacioSeleccionadoLlegadaTramo = document.getElementById("tipoEspacioLlegadaTramo")

// medios de transporte

const medioDeTransporte = document.getElementById("medioTransporte");

const servicioContratado = document.getElementById("divServicioContratado")
const noMotorizado = document.getElementById("divNoMotorizado")
const transportePublico = document.getElementById("divTransportePublico")
const vehiculoParticular = document.getElementById("divVehiculoParticular")

let bodyTramos =  []
let bodyLlegada = "llegada"
let bodyPartida = "partida"
let bodyTransporte = "transporte"

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

botonAgregarNuevoTramo.addEventListener('click', e=> {
    e.preventDefault();
    console.log("aprete el boton para agregar un tramo nuevo")
    cargarNuevoTramo();
})


espacioSeleccionadoPartidaTramo.addEventListener('change', e=> {
    e.preventDefault();

    console.log("espacio seleccionado partida tramo")
    mostrarOpcionesSegunClaseTramoPartida(espacioSeleccionadoPartidaTramo.value);
})

espacioSeleccionadoLlegadaTramo.addEventListener('change', e=> {
    e.preventDefault();
    console.log("espacio seleccionado llegada tramo")
    mostrarOpcionesSegunClaseTramoLlegada(espacioSeleccionadoLlegadaTramo.value);
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

function cargarNuevoTramo() {
    //Esta funcion se va a encargar de:
    // 1. modificar el html para que se vayan mostrando los tramos que voy teniendo
    // 2. va a empezar a armar el body

    bodyPartida = definirBodyPartida()
    bodyLlegada = definirBodyLlegada()
    bodyTransporte = definirBodyTransporte(medioDeTransporte.value)

    let bodyParcial = {
        partida: bodyPartida,
        llegada: bodyLlegada,
        transporte: bodyTransporte
    }
    bodyTramos.push(bodyParcial)
    console.log(bodyTramos)
    console.log(bodyTramos.length)

}

function definirBodyTransporte(medioTransporteSeleccionado) {

    let claseAInicializar = "";

    if(medioTransporteSeleccionado === "servicioContratado") {
        claseAInicializar = "servicioContratado"
        const valorServicioContratado = document.getElementById("servicioContratado").value
        bodyTransporte = {
            clase: claseAInicializar,
            tipoServicioContratado: valorServicioContratado
        }

    }else if (medioTransporteSeleccionado === "transporteNoMotorizado"){
        claseAInicializar = "transporteNoMotorizado"
        const valorNoMotorizado = document.getElementById("noMotorizado").value
        bodyTransporte = {
            clase: claseAInicializar,
            tipoNoMotorizado: valorNoMotorizado
        }

    }else if (medioTransporteSeleccionado === "transportePublico"){
        claseAInicializar = "transportePublico"
        const tipoTransportePublico = document.getElementById("transportePublico").value
        const valorLinea = document.getElementById("nombreTransporte")
        bodyTransporte = {
            clase: claseAInicializar,
            tipoTransporte: tipoTransportePublico,
            nombre: valorLinea
        }
    }else {
        claseAInicializar = "vehiculoParticular"
        const valorVehiculoParticular = document.getElementById("vehiculoParticular").value
        const valortipoCombustible = document.getElementById("tipoCombustible").value
        bodyTransporte = {
            clase: claseAInicializar,
            tipoVehiculoParticular: valorVehiculoParticular,
            tipoCombustible: valortipoCombustible
        }

    }
    return  bodyTransporte
}

const espacioTrabajoTramoPartida = document.getElementById("claseEspacioTrabajoPartidaTramo")
const espacioHogarTramoPartida = document.getElementById("claseHogarPartidaTramo")


function mostrarOpcionesSegunClaseTramoPartida(valor) {
    if(valor === "trabajo") {
        console.log("trabajooooooooo")
        espacioTrabajoTramoPartida.classList.remove("hidden");
        espacioHogarTramoPartida.classList.add("hidden");
    }else{
        if(valor === "hogar") {
            console.log("hogaaaaaaaaaaaaaaaaaar")
            espacioTrabajoTramoPartida.classList.add("hidden");
            espacioHogarTramoPartida.classList.remove("hidden");
        } else{
            if(valor === "parada"){
                espacioTrabajoTramoPartida.classList.add("hidden");
                espacioHogarTramoPartida.classList.add("hidden");
            }
        }
    }
}

const espacioTrabajoTramoLlegada = document.getElementById("claseEspacioTrabajoLlegadaTramo")
const espacioHogarTramoLlegada = document.getElementById("claseHogarLlegadaTramo")

function mostrarOpcionesSegunClaseTramoLlegada(valor) {
    if(valor === "trabajo") {
        console.log("trabajooooooooo")
        espacioTrabajoTramoLlegada.classList.remove("hidden");
        espacioHogarTramoLlegada.classList.add("hidden");
    }else{
        if(valor === "hogar") {
            console.log("hogaaaaaaaaaaaaaaaaaar")
            espacioTrabajoTramoLlegada.classList.add("hidden");
            espacioHogarTramoLlegada.classList.remove("hidden");
        } else{
            if(valor === "parada"){
                espacioTrabajoTramoLlegada.classList.add("hidden");
                espacioHogarTramoLlegada.classList.add("hidden");
            }
        }
    }
}



function definirBodyPartida(espacioSeleccionado) {



}

function definirBodyLlegada(espacioSeleccionado) {
    let claseAInicializar = "";

    if (espacioSeleccionado === "hogar") {
        claseAInicializar = "hogar"
        const valorHogar = document.getElementById("tipoEsp").value
        bodyTransporteConstruido = {
            clase: claseAInicializar,
            tipoServicioContratado: valorServicioContratado
        }
    }
}



