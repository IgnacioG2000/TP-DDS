const ID_SESION = "idSesion"
const obtenerSesion = () => localStorage.getItem(ID_SESION)


//ESTA FUNCION LA USAMOS SI HAY QUE REDIRIGIR A UNA URL QUE NECESITA EL ID DE SESIÓN COMO PARTE DEL HEADER
const cambiarUrl = (url = "/") => {
    const idSesion = obtenerSesion()

    if (!idSesion) {
        window.location = "/login"
    } else {
        window.location = `/${url}?sesion=${idSesion}`
    }
}

document.getElementById("registro").addEventListener("submit", (e) => {
   // e.preventDefault() // Evitamos que el <form> cambie la URL
    cambiarUrl("/home")
})


