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
                <h1 style="color: #58889C">Cadastre-se</h1>
                <hr>
            </div>            
            <div class="row pb-3">
                <div class="col-8 mx-auto">
                    <form  action="CadastrarCliente" method="post" style="color: #58889C">
                        <div class="form-group">
                            <label for="inputName">Nome Completo</label>
                            <input type="text" name="nome" class="form-control" id="inputName" placeholder="Insira seu nome completo...">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputUsername">Nome de Usuário</label>
                                <input type="name" name="login" class="form-control" id="inputUsername" placeholder="Insira seu nome de usuário...">
                            </div>
                            <div class="form-group col-md-6">
                                    <label for="inputEmail">Email</label>
                                    <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Insira seu email...">
                                </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAddress">Endereço</label>
                            <input type="text" name="endereco" class="form-control" id="inputAddress" placeholder="Insira seu endereço...">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputPassword">Senha</label>
                                <input type="password" name="senha" class="form-control" id="inputPassword" placeholder="Insira sua senha...">
                            </div>
                        </div> 
                        <button type="submit" class="btn dark mt-3 mb-5 sm-0" type="submit" style="background-color: #E97568">Cadastrar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>