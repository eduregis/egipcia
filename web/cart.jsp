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
                <h1 style="color: #58889C">Seu carrinho!</h1>
                <hr>
            </div>            
            <div class="row pb-3">
                <div class="col-4 mb-3">
                    <div class="card mx-3" style="width: 18rem; border: solid 1px; color: #9A9174">
                        <img class="card-img-top p-3" src="assets/egipcia-logo-dark.png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">Nome do Produto</h5>
                            <p class="card-text" style="color: #51aa3a">R$9,99</p>     
                            <div class="form-check form-check-inline mb-3">
                                <label class="form-check-label">Quantidade: </label> 
                                <input class="form-check-input ml-3" type="number" value="2" style="width: 50px">
                            </div>    
                            <form class="form-inline">                                
                                <div class="form-group col-md-6">
                                    
                                </div>                                
                            </form>  
                            <button class="btn dark my-2" style="background-color: #E97568">Remover do carrinho</button>
                        </div>                              
                    </div>
                </div> 
                <div class="col-4 mb-3">
                    <div class="card mx-3" style="width: 18rem; border: solid 1px; color: #9A9174">
                        <img class="card-img-top p-3" src="assets/egipcia-logo-dark.png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">Nome do Produto</h5>
                            <p class="card-text" style="color: #51aa3a">R$9,99</p>     
                            <div class="form-check form-check-inline mb-3">
                                <label class="form-check-label">Quantidade: </label> 
                                <input class="form-check-input ml-3" type="number" value="2" style="width: 50px">
                            </div>    
                            <form class="form-inline">                                
                                <div class="form-group col-md-6">
                                    
                                </div>                                
                            </form>  
                            <button class="btn dark my-2" style="background-color: #E97568">Remover do carrinho</button>
                        </div>                              
                    </div>
                </div>
                <div class="col-4 mb-3">
                    <div class="card mx-3" style="width: 18rem; border: solid 1px; color: #9A9174">
                        <img class="card-img-top p-3" src="assets/egipcia-logo-dark.png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">Nome do Produto</h5>
                            <p class="card-text" style="color: #51aa3a">R$9,99</p>     
                            <div class="form-check form-check-inline mb-3">
                                <label class="form-check-label">Quantidade: </label> 
                                <input class="form-check-input ml-3" type="number" value="2" style="width: 50px">
                            </div>    
                            <form class="form-inline">                                
                                <div class="form-group col-md-6">
                                    
                                </div>                                
                            </form>  
                            <button class="btn dark my-2" style="background-color: #E97568">Remover do carrinho</button>
                        </div>                              
                    </div>
                </div>  
            </div>
            <form class="ml-3 mt-3" style="color: #58889C">
                <div class="form-row">
                    <h3>Preço total: R$ 59,94</h3>  
                </div>                        
                <button type="submit" class="btn dark mt-3 mb-5 sm-0" type="submit" style="background-color: #E97568">Finalizar Pedido</button>
            </form>
        </div>
    </body>
</html>