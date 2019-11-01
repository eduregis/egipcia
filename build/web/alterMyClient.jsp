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
                <h1 style="color: #58889C">Atualizar Cliente</h1>
                <hr>
            </div>            
            <%
            /* Recupera da requisição um objeto que representa um cliente */
            Cliente clienteAlter = (Cliente) request.getAttribute("cliente");
            /* Se o objeto que representa o cliente for diferente de nulo */
            if (clienteAlter != null) {
            /* Exibir os dados do cliente no formulário HTML para que o usuário possa editá-las */
            %>
            <div class="row pb-3">              
                <div class="row">
                    <div class="col">
                        <div class="row pb-3">
                            <div class="mx-5">
                                <form action="AtualizarCliente" method="post" style="color: #58889C">
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="inputId">Id</label>
                                            <input class="form-control mr-sm-2" type="text" name="id" value="<%= clienteAlter.getId() %>" readonly="readonly">
                                        </div>
                                        <div class="form-group col-md-10">
                                            <label for="inputName">Nome Completo</label>
                                            <input type="text" name="nome" class="form-control" id="inputName" value="<%= clienteAlter.getNome() %>">
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="inputUsername">Nome de Usuário</label>
                                            <input type="text" name="login" class="form-control" id="inputUsername" value="<%= clienteAlter.getLogin() %>">
                                        </div>
                                        <div class="form-group col-md-6">
                                                <label for="inputEmail">Email</label>
                                                <input type="text" name="email" class="form-control" id="inputEmail" value="<%= clienteAlter.getEmail() %>">
                                            </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputAddress">Endereço</label>
                                        <input type="text" name="endereco" class="form-control" id="inputAddress" value="<%= clienteAlter.getEndereco() %>">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="inputPassword">Senha</label>
                                            <input type="password" name="senha" class="form-control" id="inputPassword" value="<%= clienteAlter.getSenha() %>">
                                        </div>                                               
                                    </div> 
                                    <button type="submit" class="btn dark mt-3 mb-5 sm-0" style="background-color: #E97568">Atualizar Cliente</button>
                                </form>
                                <div class="form-group danger-zone col-md-6">
                                    <h3>Zona Perigosa!!</h3>
                                    <a class="btn dark sm-0" style="background-color: #E97568" href="DeletarCliente?id=<%= clienteAlter.getId() %>">Excluir minha conta</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
            } else {
            /* Exibir uma mensagem para informar que não existe o cliente a ser mostrado */
            %>
            <div>Não existe cliente a ser exibido</div>
            <%
            }
            %>
        </div>
    </body>
    <style>
        .danger-zone {
            color: #E97568;
            background-color: #e3c4c1;
            border: solid 2px;
            border-radius: 3px;
            padding: 10px;
        }
    </style>
</html>