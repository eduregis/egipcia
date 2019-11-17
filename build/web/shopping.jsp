<%@page import="model.cliente.ClienteModel"%>
<%@page import="model.compra.CompraModel"%>
<%@page import="model.produto.Produto"%>
<%@page import="model.produto.ProdutoModel"%>
<%@page import="model.compraProduto.CompraProduto"%>
<%@page import="model.compraProduto.CompraProdutoModel"%>
<%@page import="model.compra.Compra"%>
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
                <h1 style="color: #58889C">Minhas Compras</h1>
                <hr>
            </div>  
            <div class="row pb-3">              
                <div id="listCategoriesDiv">
                    <div class="row pb-3">
                        <div class="mx-5">
                            <h2 style="color: #58889C">Lista de Compras</h2>
                            <%
                                /* Recupera da requisição um objeto que representa uma lista de compras */
                                CompraModel compraModel = new CompraModel();
                                List<Compra> compras = compraModel.listar();
                                /* Se o objeto que representa lista de compras for diferentes de nulo e não for uma lista vazia */
                                if (compras != null && compras.size() > 0){
                                    /* Exibir os dados de categorias em uma tabela HTML */
                            %>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Nome do Cliente</th>
                                        <th scope="col">Nome do Produto</th>
                                        <th scope="col">Quantidade</th>
                                        <th scope="col">Data da compra</th>    
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        CompraProdutoModel compraProdutoModel = new CompraProdutoModel();
                                        ProdutoModel produtoModel = new ProdutoModel();
                                        ClienteModel clienteModel = new ClienteModel();
                                        for (Compra c: compras) {
                                            List<CompraProduto> compraProdutos = compraProdutoModel.listar(c.getId());
                                            Cliente cli = clienteModel.listar(c.getCliente_id());
                                            for (CompraProduto cp: compraProdutos){
                                                Produto p = produtoModel.listar(cp.getProduto_id());                                                
                                    %>
                                    <tr>
                                        <td><%=cli.getNome()%></td>
                                        <td><%=p.getNome()%></td>
                                        <td><%=cp.getQuantidade() %></td>
                                        <td><%=c.getData() %></td>
                                    </tr>
                                    <%
                                        }
                                    }
                                    %>
                                </tbody>
                            </table>
                            <%
                            } else {
                            /* Exibir uma mensagem para informar que não existem compras a serem exibidas */
                            %>
                            <div>Não existem compras a serem exibidas</div>
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