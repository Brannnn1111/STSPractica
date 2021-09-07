async function addImagenPersona(cedula){
        var imagenbase64 = document.getElementById("picture").src;
        console.log(imagenbase64);
       const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS/imagenes/addImagenPersona?cedula='+ cedula, {
        method: 'POST',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123',
            imagen : imagenbase64
        }
    });
    const respuesta = await request.json();
    var resultado = respuesta.message;
    
    if(resultado != "true"){
        alert("Se guardo imagen de Perdona con cedula= "+ cedula);
    } else {
        alert("No se guardo la imagen de la Persona")
    }
}

