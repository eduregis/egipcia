<%@page import="model.cliente.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <head>
        <meta name="charset" content="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />        
        <title>Egipcia - Ecommerce</title>
        <link rel="shortcut icon" href="assets/favicon.png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>        
    </head>
    <body style="background-color: #F4E19F">
        <%@include file="header.jsp"%> 
        <div class="container rounded mb-5" style="background-color: #F7EFE2">
            <div class="mt-3 ml-3 py-3">
                <h1 style="color: #58889C">Entrar</h1>
                <hr>
            </div>            
            <div class="row pb-3">
                <div class="col-8 mx-auto">
                    <form action="Login" method="post" style="color: #58889C">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputLogin">Usuário</label>
                                <input type="text" name="login" class="form-control" id="inputLogin" placeholder="Insira seu usuário...">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputPassword">Senha</label>
                                <input type="password" name="senha" class="form-control" id="inputPassword" placeholder="Insira sua senha...">
                            </div>
                        </div>                        
                        <button type="submit" class="btn dark mt-3 mb-5 sm-0" type="submit" style="background-color: #E97568">Entrar</button>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
</html>