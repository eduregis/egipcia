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
                                            <label for="inputName">Nome Completo</label>
                                            <input type="text" name="nome" class="form-control" id="inputName" placeholder="Insira o nome completo...">
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputUsername">Nome de Usuário</label>
                                                <input type="text" name="login" class="form-control" id="inputUsername" placeholder="Insira o nome de usuário...">
                                            </div>
                                            <div class="form-group col-md-6">
                                                    <label for="inputEmail">Email</label>
                                                    <input type="text" name="email" class="form-control" id="inputEmail" placeholder="Insira o email...">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputAddress">Endereço</label>
                                            <input type="text" name="endereco" class="form-control" id="inputAddress" placeholder="Insira o endereço...">
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="inputPassword">Senha</label>
                                                <input type="text" name="senha" class="form-control" id="inputPassword" placeholder="Insira a senha...">
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
            <div class="ml-3">
                <div>                    
                    <button class="btn dropdown-toggle dark my-sm-0" type="button" style="background-color: #E97568" data-toggle="collapse" data-target="#listClientDiv" aria-expanded="false" aria-controls="multiCollapseExample2">Listar clientes</button>
                    <hr>
                </div>
            </div>
            <div class="row pb-3">              
                <div class="collapse multi-collapse" id="listClientDiv">
                    <div class="row pb-3">
                        <div class="mx-5">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">id</th>
                                        <th scope="col">Nome Completo</th>
                                        <th scope="col">Nome de Usuário</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Senha</th>
                                        <th scope="col">Endereço</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>#58889C</td>
                                        <td>Jonathan Joestar</td>
                                        <td>original_jojo</td>
                                        <td>jojo@hotmail.com</td>
                                        <td>erinaS2</td>
                                        <td>England</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>#E97568</td>
                                        <td>Dio Brando</td>
                                        <td>kono_dio_da</td>
                                        <td>diobrando@hotmail.com</td>
                                        <td>wryyyyyy</td>
                                        <td>England</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>#F4E19F</td>
                                        <td>Giorno Giovanna</td>
                                        <td>arrive_derci</td>
                                        <td>giogio@gmail.com</td>
                                        <td>goldenwind123</td>
                                        <td>ItÃ¡lia</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>                            
                    </div>    
                </div>
            </div>
        </div>
    </body>
</html>