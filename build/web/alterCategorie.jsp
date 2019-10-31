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
                <h1 style="color: #58889C">Atualizar Categoria</h1>
                <hr>
            </div>            
            <%
            /* Recupera da requisição um objeto que representa uma categoria */
            Categoria categoria = (Categoria) request.getAttribute("categoria");
            /* Se o objeto que representa a categoria for diferente de nulo */
            if (categoria != null) {
            /* Exibir os dados da categoria no formulário HTML para que o usuário possa editá-las */
            %>
            <div class="row pb-3">              
                <div class="row">
                    <div class="col">
                        <div class="row pb-3">
                            <div class="mx-5">
                                <form action="AtualizarCategoria" method="post" class="m-3">
                                    <div class="form-group">
                                        <label for="inputId">Id</label>
                                        <input class="form-control mr-sm-2" type="text" name="id" value="<%= categoria.getId() %>">
                                    </div>
                                    <div class="form-group">
                                        <label for="inputId">Descricao</label>
                                        <input class="form-control mr-sm-2" type="text" name="descricao" value="<%= categoria.getDescricao() %>">
                                    </div>
                                    <button class="btn dark my-sm-0" type="submit" style="background-color: #E97568">Atualizar Categoria</button>                
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
</html>