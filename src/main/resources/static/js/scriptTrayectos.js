const espacioSeleccionadoPartida = document.getElementById("tipoEspacioPartida")
const espacioSeleccionadoLlegada = document.getElementById("tipoEspacioLlegada")
const formularioTrayectos = document.getElementById("trayectos")
const formularioTramos = document.getElementById("tramos")

const opcionPartida = document.getElementById("tipoPartida");
const opcionLlegada = document.getElementById("tipoLlegada");

const opcionesPartida = document.getElementById("opcionesPartida")
const opcionesLlegada = document.getElementById("opcionesLlegada")
let idEspacioPartida = null
let idEspacioLlegada = null

const bodyParaElBack = {}

function mostrarSegunRespuestaTrayecto(valor, discriminador) {


    if(valor === "nuevo") {
    document.getElementById("espacioNuevo" + discriminador).classList.remove("hidden")
      console.log("estoy en el nuevo")
        //mostrar el nuevo pedazo de html que permite agregar cosas
    }else {
        document.getElementById("espaciosExistentes" + discriminador).classList.remove("hidden")
    }
}

formularioTrayectos.addEventListener('submit', e=> {
    e.preventDefault();
    mostrarAgregarTramos();
})

opcionLlegada.addEventListener('change', e=> {
    e.preventDefault();
    opcionLlegada.classList.add("hidden");
    mostrarSegunRespuestaTrayecto(opcionLlegada.value, "Llegada");
})

opcionPartida.addEventListener('change', e=> {
    e.preventDefault();
    opcionPartida.classList.add("hidden");
    mostrarSegunRespuestaTrayecto(opcionPartida.value, "Partida");
})

espacioSeleccionadoPartida.addEventListener('change', e => {
    e.preventDefault();
    mostrarOpcionesSegunClasePartida(espacioSeleccionadoPartida.value);
});

espacioSeleccionadoLlegada.addEventListener('change', e => {
    e.preventDefault();
    mostrarOpcionesSegunClaseLlegada(espacioSeleccionadoLlegada.value);
});


const espacioTrabajoPartida = document.getElementById("claseEspacioTrabajoPartida");
const espacioHogarPartida= document.getElementById("claseHogarPartida");


const espacioTrabajoLlegada = document.getElementById("claseEspacioTrabajoLlegada");
const espacioHogarLlegada = document.getElementById("claseHogarLlegada");

function mostrarOpcionesSegunClasePartida(valor) {
    if(valor === "trabajo") {
        console.log("trabajooooooooo")
        espacioTrabajoPartida.classList.remove("hidden");
        espacioHogarPartida.classList.add("hidden");
    }else{
        if(valor === "hogar") {
            console.log("hogaaaaaaaaaaaaaaaaaar")
            espacioTrabajoPartida.classList.add("hidden");
            espacioHogarPartida.classList.remove("hidden");
        } else{
            if(valor === "parada"){
                espacioTrabajoPartida.classList.add("hidden");
                espacioHogarPartida.classList.add("hidden");
            }
        }
    }
}


function mostrarOpcionesSegunClaseLlegada(valor) {
    if(valor === "trabajo") {
        console.log("trabajooooooooo")
        espacioTrabajoLlegada.classList.remove("hidden");
        espacioHogarLlegada.classList.add("hidden");
    }else{
        if(valor === "hogar") {
            console.log("hogaaaaaaaaaaaaaaaaaar")
            espacioTrabajoLlegada.classList.add("hidden");
            espacioHogarLlegada.classList.remove("hidden");
        } else{
            if(valor === "parada"){
                espacioTrabajoLlegada.classList.add("hidden");
                espacioHogarLlegada.classList.add("hidden");
            }
        }
    }
}

function mostrarAgregarTramos() {
    formularioTrayectos.classList.remove("trayectos");
    formularioTrayectos.classList.add("hidden");
    formularioTramos.classList.remove("hidden");
    formularioTrayectos.classList.add("trayectos");
}







