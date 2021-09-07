/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

async function crearPersona(){
    // variables addPersona {"cedula": "?", "nombre": "?", "telefono": "?", "operador": "?"}
    var cedula   = document.getElementById("idcedula").value;
    var nombre   = document.getElementById("idnombre").value;
    var telefono = document.getElementById("idtelefono").value;
    var operador = document.getElementById("idoperador").value;
    var validacionContenido = " ";
    
    if(cedula == "0"){
        validacionContenido += "Se necesita Cédula,";
    }
    if(nombre == ""){
        validacionContenido += "Se necesita Nombre,";
    }
    if(telefono == "0"){
        validacionContenido += "Se neceista Telefono,";
    }
    if(operador == ""){
        validacionContenido += "Se necesita Operador";
    }
    
    if(validacionContenido == " "){
    //call POST /personas/addPersona
        const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS/personas/addPersona?cedula='+ cedula +'&nombre='+ nombre +'&telefono='+ telefono +'&operador='+ operador, {
        method: 'POST',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123'
        }
    });
    const respuesta = await request.json();
    var validacion = respuesta.message;
    
    //obtener status
    if(validacion != "true"){
        alert("No se guardo persona")
    } else {
        alert("Persona guardada con exito");
    }
} else {
    alert(validacionContenido);
}
}




