<%@page import="model.produto.Produto"%>
<%@page import="model.categoria.Categoria"%>
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
                <h1 style="color: #58889C">Atualizar Produto</h1>
                <hr>
            </div>            
            <%
            /* Recupera da requisição um objeto que representa um cliente */
            Produto produto = (Produto) request.getAttribute("produto");
            /* Se o objeto que representa o cliente for diferente de nulo */
            if (produto != null) {
            /* Exibir os dados do cliente no formulário HTML para que o usuário possa editá-las */
            %>
            <div class="row pb-3">              
                <div class="row">
                    <div class="col">
                        <div class="row pb-3">
                            <div class="mx-5">
                                <form action="UploadFotoProduto" method="post" enctype="multipart/form-data">
                                    <div class="from-group">
                                        <% if (produto.getFoto() != null && produto.getFoto().trim().length() > 0) { %>
                                        <div><img id="foto" src="MostrarFotoProduto?foto=<%= produto.getFoto()%>" style="max-width: 300px;" /></div>
                                        <a href="DownloadFotoProduto?foto=<%= produto.getFoto()%>">Download da Foto</a>
                                        <br>
                                        <% } else { %>
                                            <div>Este produto ainda não tem foto</div>
                                        <% } %>
                                    </div>
                                    <div class="form-row">
                                        <input type="hidden" name="id" value="<%= produto.getId()%>" />
                                        <div class="fileUpload btn" style="background-color: #E97568">
                                            <span>Upload</span>
                                            <input type="file" class="form-control upload" id="inputFoto" name="foto" placeholder="Esolha uma foto para o produto" />
                                        </div>
                                        <button type="submit" class="btn dark mt-3 mb-2 sm-0" style="background-color: #E97568">Salvar Foto</button>
                                    </div>
                                </form>
                                <form action="AtualizarProduto" method="post" style="color: #58889C">
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="inputId">Id</label>
                                            <input class="form-control mr-sm-2" type="text" name="id" value="<%= produto.getId() %>" readonly="readonly">
                                        </div>
                                        <div class="form-group col-md-10">
                                            <label for="inputName">Nome</label>
                                            <input type="text" name="nome" class="form-control" id="inputName" value="<%= produto.getNome() %>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDesc">Descricao</label>
                                        <input type="text" name="descricao" class="form-control" id="inputDesc" value="<%= produto.getDescricao() %>">
                                    </div>
                                    <div class="form-row">                                           
                                        <div class="form-group col-md-6">
                                            <label for="inputQtd">Quantidade no estoque:</label>
                                            <input type="number" name="qtd" class="form-control" id="inputQtd" value="<%= produto.getQuantidade() %>">
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="inputPrice">Preço (unidade):</label>
                                            <input type="number" step="0.01" name="preco" class="form-control" id="inputAddress" value="<%= produto.getPreco() %>">
                                        </div>
                                    </div>                                        
                                    <div class="form-group">
                                        <label for="inputImage">Imagem</label>
                                        <input type="text" name="imagem" class="form-control" id="inputImage" value="<%= produto.getFoto() %>">
                                    </div>
                                    <div class="form-group col-md-8">
                                        <%
                                        String categoriasCadastradas = "";
                                        List<Categoria> produtoCategorias = produto.getCategorias();
                                        for (int i = 0; produtoCategorias != null && i < produtoCategorias.size(); i++) {
                                            categoriasCadastradas += produtoCategorias.get(i).getId();
                                            if (i < produtoCategorias.size() - 1) {
                                                categoriasCadastradas += ";";
                                            }
                                        }
                                        %>
                                        <input type="hidden" id="inputCategorias" name="categorias" value="<%= categoriasCadastradas %>" />
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
                                    <button type="submit" class="btn dark mt-3 mb-5 sm-0" style="background-color: #E97568">Atualizar Produto</button>
                                </form>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
            <%
            } else {
            /* Exibir uma mensagem para informar que não existe a categoria a ser mostrada */
            %>
            <div>Não existe categoria a ser exibida</div>
            <%
            }
            %>
        </div>
    </body>
    
    <style>
        .fileUpload {
            position: relative;
            overflow: hidden;
            margin: 15px 20px 0px 2px;
            height: 38px;
        }
        .fileUpload input.upload {
            position: absolute;
            top: 0;
            right: 0;
            margin: 0;
            padding: 0;
            font-size: 20px;
            cursor: pointer;
            opacity: 0;
            filter: alpha(opacity=0);
        }
    </style>
    <script type="text/javascript" src="js/produto.js"></script>
</html>