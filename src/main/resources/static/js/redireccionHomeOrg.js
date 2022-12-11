
document.getElementById("navVinculaciones").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlConIdSesion("gestionar/vinculaciones")

})

document.getElementById("navAnual").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("calculadora/organizacion/hcAnual")

})

document.getElementById("navMensual").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("calculadora/organizacion/hcMensual")

})

document.getElementById("navHCTotal").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("generar_reporte_hc_total")

})

document.getElementById("navEvolucionHC").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("generar_reporte_evolucion")

})

document.getElementById("navComposicionHC").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("generar_reporte_composicion")

})

function mandarAlBack() {
    fetch('http://localhost:8080/registrarTrayecto', {
        body: {
            idSesion: obtenerSesion()
        },
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(this.body)
    }).then()
}

document.getElementById("cerrarSesion").addEventListener('click', e=> {
    e.preventDefault()
    cerrarSesion()
})

