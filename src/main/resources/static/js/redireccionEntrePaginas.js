let espaciosExistentesBack = {}
let trayectosExistentesBack = {}
let tramosExistentesBack = {}

document.getElementById("navRegistrarTrayecto").addEventListener('click', e=> {
    e.preventDefault()
    //pedirAlBack().then()
    cambiarUrlSinIdSesion("registrarTrayecto")
    //pedirle al back todo lo que tienen guardado

})


document.getElementById("cerrarSesion").addEventListener('click', e=> {
    e.preventDefault()
    cerrarSesion()
})

const pedirAlBack = async() => {

    //espaciosExistentesBack = await pedirEspacios()
    trayectosExistentesBack = await pedirTrayectos()
    console.log(trayectosExistentesBack)
   // tramosExistentesBack = await pedirTramos()

}

const pedirEspacios = async() => {
    const backResponse = await fetch('http://localhost:8080/espacios', {
        headers: {
            "Content-Type": "application/json"
        },
        method: "GET"
    })
    return backResponse.json()

}

const pedirTrayectos = async() => {
    const backResponse = await fetch('http://localhost:8080/trayectos', {
        headers: {
            "Content-Type": "application/json"
        },
        method: "GET"
    })

    return backResponse.json()
}

const pedirTramos = async() => {

    const backResponse = await fetch('http://localhost:8080/tramos', {
        headers: {
            "Content-Type": "application/json"
        },
        method: "GET"
    })

    return backResponse.json()
}