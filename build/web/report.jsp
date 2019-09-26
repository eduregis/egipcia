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
                <h1 style="color: #58889C">Relatórios</h1>
                <hr>
            </div>            
            <div class="ml-3">
                    <div>                    
                        <button class="btn dropdown-toggle dark my-sm-0" type="button" style="background-color: #E97568" data-toggle="collapse" data-target="#listClientDiv" aria-expanded="false" aria-controls="multiCollapseExample2">Exibir lista de compras por cliente </button>
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
                                            <th scope="col">Nome do Cliente</th>
                                            <th scope="col">Quantidade de compras</th>
                                            <th scope="col">Quantidade gasta</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>#58889C</td>
                                            <td>Jonathan Joestar</td>
                                            <td>2</td>
                                            <td>39,97</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>#E97568</td>
                                            <td>Dio Brando</td>
                                            <td>3</td>
                                            <td>49,95</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <td>#F4E19F</td>
                                            <td>Giorno Giovanna</td>
                                            <td>1</td>
                                            <td>9,99</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>                            
                        </div>    
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>