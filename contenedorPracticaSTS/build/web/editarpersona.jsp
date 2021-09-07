<%-- 
    Document   : editarpersona
    Created on : 7/09/2021, 12:17:54 AM
    Author     : BRANDON RODRIGUEZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <script src="js/personaUpdate.js"></script>

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<%
  String _persona = request.getParameter("persona");
%>

<script>
    window.onload = cargarDatosPersona('<%=_persona%>');
    

    
</script>
<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Editar persona</h1>
                            </div>
                            <form class="user">
                                <div class="form-group">
                                        <input class="form-control form-control-user" id="idcedula"
                                            placeholder="Cedula" type="number" min="1" pattern="^[0-9]+" value="0">
                                    
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="idnombre"
                                        placeholder="nombre" >
                                </div>
                                <div class="form-group">
                                    <input  class="form-control form-control-user" id="idtelefono"
                                        placeholder="Telefono" type="number" min="1" pattern="^[0-9]+" value="0">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="idoperador"
                                        placeholder="Operador">
                                </div>
                                <a class="btn btn-primary btn-user btn-block" id="idbtncrearpersona" onclick="editarPersonaCambios('<%=_persona%>')">
                                    Editar Datos
                                </a>
                            </form>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/personasAdd.js"></script>
    <script src="js/personaUpdate.js"></script>

</body>

</html>
