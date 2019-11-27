<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="model.categoria.CategoriaModel"%>
<%@page import="model.produto.ProdutoModel"%>
<%@page import="model.produtoCategoria.ProdutoCategoria"%>
<%@page import="model.produtoCategoria.ProdutoCategoriaModel"%>
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
        <div class="mt-3 mx-5 py-3">
            <% String nome = (String) request.getAttribute("nome");%>
            <h1 style="color: #58889C">Resultados da busca por: <%=nome %></h1>
            <hr>
        </div>
        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && produtos.size() > 0) {
        %>
        <div class="row pb-3 mx-5">
            <%
                NumberFormat numberFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
                for (Produto p : produtos) { 
            %>
            <div class="col-4 mb-5">
                <div class="card mx-auto" style="width: 18rem; border: solid 1px; color: #9A9174">
                    <div class="card-body">
                        <div class="card-header-container">
                            <div class="card-img-top p-3 img-product-container mr-3">
                                <% if (p.getFoto() != null && p.getFoto().trim().length() > 0) { %>
                                    <img id="foto" class="img-product" src="MostrarFotoProduto?foto=<%= p.getFoto()%>" />
                                <% } else { %>
                                <img class="img-product" src="assets/egipcia-logo-dark.png" alt="Card image cap">
                                <% } %>
                            </div>
                            <div>
                                <h5 class="card-title"><%= p.getNome()%></h5>
                                <p class="card-text" style="color: #51aa3a">R$ <%= numberFormat.format(p.getPreco())%></p>                            
                                <p class="card-text">Quantidade no estoque: <span style="color: #51aa3a"><%= p.getQuantidade() %></span></p>
                            </div>
                        </div>
                        <h6>Tags: 
                            <%
                                ProdutoCategoriaModel produtoCategoriaModel = new ProdutoCategoriaModel();
                                List<ProdutoCategoria> produtoCategorias = produtoCategoriaModel.listar(p.getId());
                                for (ProdutoCategoria pc: produtoCategorias) {
                                    CategoriaModel categoriaModel = new CategoriaModel();
                            %>
                            <span class="badge badge-secondary"><%=categoriaModel.listar(pc.getCategoria_id()).getDescricao() %></span>
                            <%
                            }
                            %>
                        </h6>
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
    .card-header-container{
        display: flex;
    }
    .img-product-container{
        width: 130px;
        display: flex;
        justify-content: center;
        align-items: start;
    }
    .img-product{
       height: 150px;
       max-width: 125px;
    }
</style>
</html>