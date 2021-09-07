<%-- 
    Document   : imagenpersona
    Created on : 6/09/2021, 08:16:00 PM
    Author     : BRANDON RODRIGUEZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
    
    String tipo = request.getParameter("tipo");
    
    %>

    <body>
    <%
    
    if(tipo.equals("1")){
        String cedula = request.getParameter("cedula");
        cedula = cedula.replace("/", "").replace("imagenes", "");
        %>
    <div class="form-group">
        <input type='file' onchange="readURL(this);" />
    </div>
    
    <div class="picture-container" style="display: none;" id="campo">
        <img id="picture" class="picture" src="" >
    </div>
        
    <button style="display: none;" id="idbtnagregarimagenpersona" onclick="addImagenPersona('<%=cedula%>')">Agregar imagen a persona</button>
        <%
    }
    
    %>    
    </body>
        <script>
        
        function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $("#picture")
                    .attr('src', e.target.result)
                   
            };

            reader.readAsDataURL(input.files[0]);
            document.getElementById("campo").style.display="block";
            document.getElementById("idbtnagregarimagenpersona").style.display="block";
        }
    }
        
    </script>
    
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="js/imagenespersonas.js"></script>
</html>
