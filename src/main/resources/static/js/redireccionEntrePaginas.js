
document.getElementById("navRegistrarTrayecto").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("registrarTrayecto")


})


document.getElementById("cerrarSesion").addEventListener('click', e=> {
    e.preventDefault()
    cerrarSesion()
})

