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
                <h1 style="color: #58889C">Categorias</h1>
                <hr>
            </div>            
            <form class="form-inline m-3">
                <input class="form-control mr-sm-2" type="search" placeholder="Insira a categoria..." aria-label="Search">
                <button class="btn dark my-sm-0" type="submit" style="background-color: #E97568">Adicionar Categoria</button>                
            </form>
            <div class="row pb-3">              
                <div id="listClientDiv">
                    <div class="row pb-3">
                        <div class="mx-5">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">id</th>
                                        <th scope="col">Nome do Produto</th>                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>#58889C</td>
                                        <td>Categoria 1</td>                                        
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>#E97568</td>
                                        <td>Categoria 2</td>                                        
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>#F4E19F</td>
                                        <td>Categoria 3</td>                                        
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