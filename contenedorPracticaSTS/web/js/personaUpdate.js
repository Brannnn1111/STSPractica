/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function editarPersona(_persona){
    document.getElementById("idbtneditarpersona"+ _persona).innerText = "...";
    document.getElementById("idbtneditarpersona"+ _persona).disabled = true;
    
    location.href="editarpersona.jsp?persona="+ _persona;
}

   async function cargarDatosPersona(persona){
        
    const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS/personas/getPersona?id='+ persona, {
    method: 'GET',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123'
        }
    });
    const _persona = await request.json();
    
    for(var datos of _persona){
        
    document.getElementById("idcedula").value = datos.persona.cedula;
    document.getElementById("idnombre").value = datos.persona.nombre;
    document.getElementById("idtelefono").value = datos.persona.telefono;
    document.getElementById("idoperador").value = datos.persona.operador;
    
    }
    }
    
   async function editarPersonaCambios(persona){
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
    //call PUT /personas/updatePersona
        const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS/personas/updatePersona?id='+ persona +'&cedula='+ cedula +'&nombre='+ nombre +'&telefono='+ telefono +'&operador='+ operador, {
        method: 'PUT',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123'
        }
    });
    const respuesta = await request.json();
    var validacion = respuesta.message;
    
    //obtener status
    if(validacion != "true"){
        alert("No se editaron datos de persona")
    } else {
        alert("Persona editada con exito");
    }
} else {
    alert(validacionContenido);
}
   }

