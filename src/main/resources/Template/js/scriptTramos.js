/*
* Acá esta toda la logica referida a modificar la pantalla de tramos
* */

let n = 0
const opcionesTramo = document.getElementById("tipoTramo")

const partidaTramo = document.getElementById("partidaTramo")
const llegadaTramo = document.getElementById("llegadaTramo")
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
        partidaTramo.classList.remove("hidden")
        llegadaTramo.classList.remove("hidden")
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
    //armar los bodies
    //mostar los trayectos ya cargados

    bodyPartida = definirBodyPartida(espacioPartidaTramoNuevo.value)
    bodyLlegada = definirBodyLlegada(espacioLlegadaTramoNuevo.value)
    bodyTransporte = definirBodyTransporte(medioDeTransporte.value)

    let bodyParcial = {
        partida: bodyPartida,
        llegada: bodyLlegada,
        transporte: bodyTransporte
    }
    bodyTramos.push(bodyParcial)
    console.log(bodyTramos)

    volverAEstadoDefault()
    n++;

}

function volverAEstadoDefault() {

    //1. agregar el divisor para que se vea un tramo agregado
    let htmlNuevo = " <p> (si testean esto no se asusten, es meramente estetico, lo vamos a ver después) </p>";
    tramosCargados.innerHTML = tramosCargados.innerHTML + htmlNuevo;
    //2.  resetar los valores en las variables guardadas
    document.getElementById("tramos")
    opcionesTramo.classList.remove("hidden");
    ocultarTodo();

}

function ocultarTodo() {

    partidaTramo.classList.add("hidden")
    llegadaTramo.classList.add("hidden")


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
    let claseAInicializar;

    const latitudTramoPartida = document.getElementById("inputLatitudTramoPartida").value
    const longitudTramoPartida = document.getElementById("inputLongitudTramoPartida").value
    const provinciaTramoPartida = document.getElementById("inputProvinciaTramoPartida").value
    const localidadTramoPartida = document.getElementById("inputMunicipioTramoPartida").value
    const direccionTramoPartida = document.getElementById("inputDireccionTramoPartida").value
    const numeroPartida = document.getElementById("inputNumeroTramoPartida").value
    const codigoPostalPartida = document.getElementById("inputcodigoPostalTramoPartida").value
    let bodyEspacioPartida;
    if (espacioSeleccionado === "hogar") {
        claseAInicializar = "hogar"
        const valorHogar = document.getElementById("tipoEspacioPartidaTramo").value
        bodyEspacioPartida = {
            clase: claseAInicializar,
            tipoEspacioLlegada: valorHogar,
            latitud: latitudTramoPartida,
            longitud: longitudTramoPartida,
            localidad: localidadTramoPartida,
            provincia: provinciaTramoPartida,
            direccion: direccionTramoPartida,
            numero: numeroPartida,
            codigoPostal: codigoPostalPartida

        }
    }else if  (espacioSeleccionado === "trabajo"){
        claseAInicializar = "trabajo"
        const valorTrabajo = document.getElementById("tipoEspacioPartidaTramo").value
        bodyEspacioPartida = {
            clase: claseAInicializar,
            tipoEspacioLlegada: valorTrabajo,
            latitud: latitudTramoPartida,
            longitud: longitudTramoPartida,
            localidad: localidadTramoPartida,
            provincia: provinciaTramoPartida,
            direccion: direccionTramoPartida,
            numero: numeroPartida,
            codigoPostal: codigoPostalPartida

        }
    } else {
        claseAInicializar = "parada"
        const valorParada = document.getElementById("tipoEspacioPartidaTramo").value
        bodyEspacioPartida = {
            clase: claseAInicializar,
            bodyEspacioPartida: valorParada,
            latitud: latitudTramoPartida,
            longitud: longitudTramoPartida,
            localidad: localidadTramoPartida,
            provincia: provinciaTramoPartida,
            direccion: direccionTramoPartida,
            numero: numeroPartida,
            codigoPostal: codigoPostalPartida
        }
    }

    return bodyEspacioPartida

}

function definirBodyLlegada(espacioSeleccionado) {
    let claseAInicializar;

    const latitudTramoLlegada = document.getElementById("inputLatitudLlegadaTramo").value
    const longitudTramoLlegada = document.getElementById("inputLongitudLlegadaTramo").value
    const provinciaTramoLlegada = document.getElementById("inputProvinciaLlegadaTramo").value
    const localidadTramoLlegada = document.getElementById("inputMunicipioLlegadaTramo").value
    const direccionTramoLlegada = document.getElementById("inputDireccionLlegadaTramo").value
    const numeroLlegada = document.getElementById("inputNumeroLlegadaTramo").value
    const codigoPostalLlegada = document.getElementById("inputCodigoPostalLlegadaTramo").value

    let bodyEspacioLlegada;
    if (espacioSeleccionado === "hogar") {
        claseAInicializar = "hogar"
        const valorHogar = document.getElementById("tipoEspacioLlegadaTramo").value
        bodyEspacioLlegada = {
            clase: claseAInicializar,
            tipoEspacioLlegada: valorHogar,
            latitud: latitudTramoLlegada,
            longitud: longitudTramoLlegada,
            localidad: localidadTramoLlegada,
            provincia: provinciaTramoLlegada,
            direccion: direccionTramoLlegada,
            numero: numeroLlegada,
            codigoPostal: codigoPostalLlegada
        }
    }else if  (espacioSeleccionado === "trabajo"){
       claseAInicializar = "trabajo"
        const valorTrabajo = document.getElementById("tipoEspacioLlegadaTramo").value
        bodyEspacioLlegada = {
            clase: claseAInicializar,
            tipoEspacioLlegada: valorTrabajo,
            latitud: latitudTramoLlegada,
            longitud: longitudTramoLlegada,
            localidad: localidadTramoLlegada,
            provincia: provinciaTramoLlegada,
            direccion: direccionTramoLlegada,
            numero: numeroLlegada,
            codigoPostal: codigoPostalLlegada
        }
    } else {
        claseAInicializar = "parada"
        const valorParada = document.getElementById("tipoEspacioLlegadaTramo").value
        bodyEspacioLlegada = {
            clase: claseAInicializar,
            tipoEspacioLlegada: valorParada,
            latitud: latitudTramoLlegada,
            latitud: latitudTramoLlegada,
            longitud: longitudTramoLlegada,
            localidad: localidadTramoLlegada,
            provincia: provinciaTramoLlegada,
            direccion: direccionTramoLlegada,
            numero: numeroLlegada,
            codigoPostal: codigoPostalLlegada
        }
    }

    return bodyEspacioLlegada
}



