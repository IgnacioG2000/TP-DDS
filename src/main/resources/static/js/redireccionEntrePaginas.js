
document.getElementById("navRegistrarTrayecto").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
     cambiarUrlConIdSesion("registrarTrayecto")

})
function mandarAlBack() {
    fetch('http://localhost:8080/registrarTrayecto', {
        body: {
            idSesion: localStorage.getItem(ID_SESION)
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

