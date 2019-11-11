<%@page import="model.produto.ProdutoDAO"%>
<%@page import="model.carrinhocompra.CarrinhoCompraItem"%>
<%@page import="model.carrinhocompra.CarrinhoCompraModel"%>
<%@page import="java.util.List"%>
<%@page import="model.cookie.CookieUtils"%>
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
        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null && mensagem.length() > 0) {
        %>
        <div class="container" style="color: #E97568"><%=mensagem%></div> 
        <%
            }
        %>
        <div class="container rounded mb-5" style="background-color: #F7EFE2">
            <div class="mt-3 ml-3 py-3">
                <h1 style="color: #58889C">Seu carrinho!</h1>
                <hr>
            </div>            
            <div class="row pb-3 pl-3">
            <%
                
                Cookie c = CookieUtils.obterCookie(request); // obtém o cookie da aplicação, caso exista
        
        if (c == null) {
            // caso o cookie já exista, resgata o carrinho de compras armazenado dentro do valor do cookie
            
        } else {
            List<CarrinhoCompraItem> carrinhoCompra = CarrinhoCompraModel.obterCarrinhoCompra(c.getValue());
            for (int i = 0; i < carrinhoCompra.size(); i++) {
                CarrinhoCompraItem cci = carrinhoCompra.get(i);
            %>
            <div class="col-4 mb-3">
                <div class="card" style="width: 18rem; border: solid 1px; color: #9A9174">
                    <img class="card-img-top p-3" src="assets/egipcia-logo-dark.png" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title"><%= cci.getProduto().getNome() %></h5>
                        <p class="card-text"><%= cci.getProduto().getPreco() %></p>                            
                        <p class="card-text">Descrição: <%= cci.getProduto().getDescricao() %></p>
                        <p style="color: #51aa3a"><%= cci.getQuantidade() %> no carrinho</p>
                        <h6>Tags: <span class="badge badge-secondary">Tag1</span> <span class="badge badge-secondary">Tag2</span></h6>
                        <button onclick="document.location='RemoverProdutoCarrinhoCompraServlet?produtoId=<%= cci.getProduto().getId() %>';" class="btn dark my-2" type="submit" style="background-color: #E97568">Remover do carrinho</button>
                    </div>                              
                </div>
            </div> 
            <%
                }
            }
            %>                   
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