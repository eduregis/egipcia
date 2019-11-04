<%@page import="model.produto.Produto"%>
<%@page import="model.categoria.Categoria"%>
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
                <h1 style="color: #58889C">Produtos</h1>
                <hr>
                <div>                    
                    <button class="btn dropdown-toggle dark my-sm-0" type="button" style="background-color: #E97568" data-toggle="collapse" data-target="#createClientDiv" aria-expanded="false" aria-controls="multiCollapseExample2">Cadastrar produto</button>
                </div>
            </div>            
            <div class="row pb-3"> 
                <div class="collapse multi-collapse" id="createClientDiv">
                    <div class="row pb-3">
                        <div class="col-12 mx-5">
                            <form action="CadastrarProduto" method="post" style="color: #58889C">
                                <div class="form-group">
                                    <label for="inputName">Nome do Produto</label>
                                    <input type="text" name="nome" class="form-control" id="inputName" placeholder="Insira o nome do produto...">
                                </div>
                                <div class="form-group">
                                    <label for="inputDesc">Descrição do Produto</label>
                                    <input type="text" name="descricao" class="form-control" id="inputDesc" placeholder="Insira a descrição do produto..."></textarea>
                                </div>
                                <div class="form-row">                                           
                                    <div class="form-group col-md-6">
                                        <label for="inputQtd">Quantidade no estoque:</label>
                                        <input type="number" name="qtd" class="form-control" id="inputQtd">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputPrice">Preço (unidade):</label>
                                        <input type="number" step="0.01" name="preco" class="form-control" id="inputAddress" placeholder="Insira o preço...">
                                    </div>
                                </div>  
                                <div class="form-row">
                                    <label for="inputCategories">Categorias</label>
                                    <input type="hidden" id="inputCategorias" name="categorias" value="<%= (request.getAttribute("categorias") != null && request.getAttribute("categorias").toString().trim().length() > 0 ? request.getAttribute("categorias").toString().trim() : "")%>" />
                                </div>
                                <div class="form-row rounded p-2" style="border: solid 1px"> 
                                    <div class="form-group col-md-8">
                                        <select id="selectCategoria" class="form-control">
                                            <option value="">Selecione...</option>
                                            <%
                                                List<Categoria> categoriasList = (List<Categoria>) request.getAttribute("categoriasList");
                                                for (int i = 0; categoriasList != null && i < categoriasList.size(); i++){
                                                    Categoria c = categoriasList.get(i);
                                            %>
                                            <option value="<%= c.getId()%>"><%= c.getDescricao()%></option>
                                            <% 
                                                }
                                            %>
                                        </select>
                                        <input type="button" value="Adicionar Categoria" onclick="adicionarCategoria();" />
                                        <div id="listCategorias"></div>
                                    </div>
                                </div>
                                <button type="submit" class="btn dark mt-3 mb-5 sm-0" style="background-color: #E97568">Cadastrar</button>
                            </form>
                        </div>                            
                    </div>
                </div>
            </div>
            <hr>
            <div class="row pb-3">              
                <div id="listClientDiv">
                    <div class="row pb-3">
                        <div class="mx-5">
                            <h2 style="color: #58889C">Lista de Produtos</h2>
                            <%
                                /* Recupera da requisição um objeto que representa uma lista de produtos */
                                List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                                /* Se o objeto que representa lista de produtos for diferentes de nulo e não for uma lista vazia */
                                if (produtos != null && produtos.size() > 0){
                                    /* Exibir os dados de produtos em uma tabela HTML */
                            %>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">id</th>
                                        <th scope="col">Nome do Produto</th>
                                        <th scope="col">Descrição</th>
                                        <th scope="col">Categoria(s)</th>
                                        <th scope="col">Quantidade</th>
                                        <th scope="col">Preço unitário</th>
                                        <th scope="col">Foto</th>
                                        <th scope="col">&nbsp;</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    for (Produto p: produtos) {
                                    %>
                                    <tr>
                                        <th scope="row"><%= p.getId()%></th>
                                       
                                        <td><%= p.getNome()%></td>
                                        <td><%= p.getDescricao()%></td>
                                        <td></td>
                                        <td><%= p.getQuantidade()%></td>
                                        <td><%= p.getPreco()%></td>
                                        <td>
                                            <% if (p.getFoto() != null && p.getFoto().trim().length() > 0) { %>
                                            <img id="foto" src="MostrarFotoProduto?foto=<%= p.getFoto()%>" width="50" height="50" />
                                            <% } else { %>
                                            Sem foto
                                            <% } %>
                                        </td>
                                        <td>
                                            <a href="MostrarProduto?id=<%= p.getId() %>" class="mx-2">
                                                <img src="assets/pencil-edit-button.png">
                                            </a>
                                            <a href="DeletarProduto?id=<%= p.getId() %>">
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
                            /* Exibir uma mensagem para informar que não existem produtoss a serem exibidos */
                            %>
                            <div>Não existem produtos a serem exibidos</div>
                            <%
                            }    
                            %>
                        </div>                            
                    </div>    
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="js/produto.js"></script>                                     
</html>