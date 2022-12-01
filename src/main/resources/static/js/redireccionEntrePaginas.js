document.getElementById("navRegistrarTrayecto").addEventListener('click', e=> {
    e.preventDefault()
    cambiarUrlSinIdSesion("registrarTrayecto")
})


document.getElementById("cerrarSesion").addEventListener('click', e=> {
    e.preventDefault()
    cerrarSesion()
})

