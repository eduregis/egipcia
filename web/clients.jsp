<%@page import="java.util.List"%>
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
                <h1 style="color: #58889C">Clientes</h1>
                <hr>
                <div>                    
                    <button class="btn dropdown-toggle dark my-sm-0" type="button" style="background-color: #E97568" data-toggle="collapse" data-target="#createClientDiv" aria-expanded="false" aria-controls="multiCollapseExample2">Cadastrar cliente</button>
                    <hr>
                </div>
            </div>            
            <div class="row pb-3">              
                <div class="row">
                    <div class="col">
                        <div class="collapse multi-collapse" id="createClientDiv">
                            <div class="row pb-3">
                                <div class="mx-5">
                                    <form action="CadastrarCliente" method="post" style="color: #58889C">
                                        <div class="form-group">
                                            <label>Nome Completo</label>
                                            <input type="text" name="nome" class="form-control" placeholder="Insira o nome completo...">
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>Nome de Usuário</label>
                                                <input type="text" name="login" class="form-control" placeholder="Insira o nome de usuário...">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>Email</label>
                                                <input type="text" name="email" class="form-control" placeholder="Insira o email...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>Endereço</label>
                                            <input type="text" name="endereco" class="form-control" placeholder="Insira o endereço...">
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>Senha</label>
                                                <input type="password" name="senha" class="form-control" placeholder="Insira a senha...">
                                            </div>                                               
                                        </div> 
                                        <button type="submit" class="btn dark mt-3 mb-5 sm-0" style="background-color: #E97568">Cadastrar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row pb-3">              
                <div id="listClientDiv">
                    <div class="row pb-3">
                        <div class="mx-5">
                            <h2 style="color: #58889C">Lista de Clientes</h2>
                            <%
                                /* Recupera da requisição um objeto que representa uma lista de categorias */
                                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                                /* Se o objeto que representa lista de categorias for difrentes de nulo e não for uma lista vazia */
                                if (clientes != null && clientes.size() > 0){
                                    /* Exibir os dados de categorias em uma tabela HTML */
                            %>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">id</th>
                                        <th scope="col">Nome Completo</th>
                                        <th scope="col">Nome de Usuário</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Senha</th>
                                        <th scope="col">Endereço</th> 
                                        <th scope="col">&nbsp;</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    for (Cliente c: clientes) {
                                    %>
                                    <tr>
                                        <td><%= c.getId()%></td>
                                        <td><%= c.getNome()%></td>
                                        <td><%= c.getLogin()%></td>
                                        <td><%= c.getEmail()%></td>
                                        <td><%= c.getSenha()%></td>
                                        <td><%= c.getEndereco()%></td>
                                        <td>
                                            <a href="MostrarCliente?id=<%= c.getId() %>" class="mx-2">
                                                <img src="assets/pencil-edit-button.png">
                                            </a>
                                            <a href="DeletarCliente?id=<%= c.getId() %>">
                                                <img src="assets/rubbish-bin.png">
                                            </a>
                                        </td>
                                    </tr>
                                    <%
                                    }
                                    %>
                                </tbody>
                            </table>
                            <%
                            } else {
                            /* Exibir uma mensagem para informar que não existem clientes a serem exibidos */
                            %>
                            <div>Não existem clientes a serem exibidos</div>
                            <%
                            }    
                            %>
                        </div>                            
                    </div>    
                </div>
            </div>
        </div>
    </body>
</html>