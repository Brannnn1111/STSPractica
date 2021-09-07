// Call the dataTables jQuery plugin
$(document).ready(function() {
    llenadoTablePersonas();
  $('#idpersonastable').DataTable();
});

async function llenadoTablePersonas(){
    const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS/personas/allPersonas', {
        method: 'GET',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123'
        }
    });
    const personas = await request.json();
    
    var listPersonas = "";
    
    for(var persona of personas){
    
    var personaJson = '<tr>\n'
                    + '  <td>'+ persona.persona.cedula +'</td>'
                    + '  <td>'+ persona.persona.nombre +'</td>'
                    + '  <td>'+ persona.persona.telefono +'</td>'
                    + '  <td>'+ persona.persona.operador +'</td>'
                    + '  <td><button class="btn btn-secondary" onclick="imagen(\''+ persona.persona.links.imagen.href+'\')">Image</button></td>'
                    + '  <td><button class="btn btn-primary" id="idbtneditarpersona'+ persona.persona._id +'" onclick="editarPersona(\''+ persona.persona._id +'\')">Editar</button></td>'
                    + '  <td><button class="btn btn-danger" onclick="eliminarPersona(\''+ persona.persona._id +'\')">Borrar</button></td>'
                    + '</tr>';
            listPersonas += personaJson;
    }
    
    document.querySelector('#idpersonastable tbody').outerHTML = listPersonas;
    
}

async function imagen(root){
    const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS'+ root, {
        method: 'GET',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123'
        }
    });
    const imagen = await request.json();
    
    var image = imagen.imagen;
    
    if(image != "undefined" || image != null){
        window.open("http://localhost:8090/contenedorPracticaSTS/imagenpersona.jsp?tipo=1&cedula="+ root, "Imagen", "width=600, height=700");
    } else {
            window.open("http://localhost:8090/contenedorPracticaSTS/imagenpersona.jsp?tipo=2&imagen="+ image, "Imagen", "width=600, height=700");
    }
}

async function eliminarPersona(persona){
    const request = await fetch('http://localhost:8090/APIRESTPractica/webresources/PracticSTS/personas/deletePersona?id='+ persona, {
        method: 'DELETE',
        headers: {
            'user': 'adminSTS',
            'ps': 'STS!123'
        }
    });
    const respuesta = await request.json();
    var validacion = respuesta.message;
    
    //obtener status
    if(validacion != "true"){
        alert("No se elimino persona")
    } else {
        alert("Persona elimino con exito");
    }
}