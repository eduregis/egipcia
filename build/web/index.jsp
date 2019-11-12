<%@page import="model.produto.Produto"%>
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
            <h1 style="color: #58889C">No estoque!</h1>
            <hr>
        </div>
        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && produtos.size() > 0) {
        %>
        <div class="row pb-3 pl-3">
            <%
                for (Produto p : produtos) { 
            %>
            <div class="col-4 mb-3">
                <div class="card" style="width: 18rem; border: solid 1px; color: #9A9174">
                    <div class="card-img-top p-3 img-product-container">
                        <% if (p.getFoto() != null && p.getFoto().trim().length() > 0) { %>
                            <img id="foto" class="img-product" src="MostrarFotoProduto?foto=<%= p.getFoto()%>" />
                        <% } else { %>
                        <img class="img-product" src="assets/egipcia-logo-dark.png" alt="Card image cap">
                        <% } %>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title"><%= p.getNome()%></h5>
                        <p class="card-text"><%= p.getPreco()%></p>                            
                        <p class="card-text card-text-desc">Descrição: <%= p.getDescricao()%></p>
                        <p class="card-text">Quantidade no estoque: <span style="color: #51aa3a"><%= p.getQuantidade() %></span></p>
                        <!-- <h6>Tags: <span class="badge badge-secondary">Tag1</span> <span class="badge badge-secondary">Tag2</span></h6> -->
                        <button onclick="document.location = 'AdicionarProdutoCarrinhoCompraServlet?produtoId=<%= p.getId() %>&quantidade=1';" class="btn dark my-2" type="submit" style="background-color: #E97568">Adicionar ao carrinho</button>
                    </div>                              
                </div>
            </div> 
            <%
                }
            %>                   
        </div>
        <%
            }
        %>    
    </div>
</body>
<style>
    .card-text-desc{
        height: 81px;
    }
    .img-product-container{
        display: flex;
        justify-content: center;
    }
    .img-product{
       height: 300px;
       max-width: 250px;
    }
</style>
</html>