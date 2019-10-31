<%@page import="model.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="model.administrador.Administrador"%>
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
                <h1 style="color: #58889C">Atualizar minha conta de Administrador</h1>
                <hr>
            </div>            
            <%
            /* Recupera da requisição um objeto que representa um cliente */
            Administrador adminAlter = (Administrador) request.getAttribute("admin");
            /* Se o objeto que representa o cliente for diferente de nulo */
            if (adminAlter != null) {
            /* Exibir os dados do admin no formulário HTML para que o usuário possa editá-las */
            %>
            <div class="row pb-3">              
                <div class="row">
                    <div class="col">
                        <div class="row pb-3">
                            <div class="mx-5">
                                <form action="AtualizarAdministrador" method="post" style="color: #58889C">
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="inputId">Id</label>
                                        <input class="form-control mr-sm-2" type="text" name="id" value="<%= adminAlter.getId() %>">
                                        </div>
                                        <div class="form-group col-md-10">
                                            <label for="inputName">Nome Completo</label>
                                            <input type="text" name="nome" class="form-control" id="inputName" value="<%= adminAlter.getNome() %>">
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="inputUsername">Nome de Usuário</label>
                                            <input type="text" name="login" class="form-control" id="inputUsername" value="<%= adminAlter.getLogin() %>">
                                        </div>
                                        <div class="form-group col-md-6">
                                                <label for="inputEmail">Email</label>
                                                <input type="text" name="email" class="form-control" id="inputEmail" value="<%= adminAlter.getEmail() %>">
                                            </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="inputPassword">Senha</label>
                                            <input type="password" name="senha" class="form-control" id="inputPassword" value="<%= adminAlter.getSenha() %>">
                                        </div>                                               
                                    </div> 
                                    <button type="submit" class="btn dark mt-3 mb-5 sm-0" style="background-color: #E97568">Atualizar Administrador</button>
                                </form>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
            <%
            } else {
            /* Exibir uma mensagem para informar que não existe o administrador a ser mostrado */
            %>
            <div>Não existe administrador a ser exibido</div>
            <%
            }
            %>
        </div>
    </body>
</html>