<%-- 
    Document   : header
    Created on : 25/09/2019, 20:18:55
    Author     : eduardo
--%>

<%@page import="model.cliente.Cliente"%>
<%@page import="model.administrador.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark container my-3 rounded" style="background-color: #58889C">
            <a class="navbar-brand my-2" href="#">
                <div class="navbar-nav form-inline">  
                    <div class="form-group">
                        <img src="assets/egipicia-logo-light.png" style="width: 50px; height: 50px" alt="">
                        <h2 class="ml-2 my-auto">EGIPCIA</h2>
                    </div> 
                </div> 
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>                
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-3">
                    <li class="nav-item active">
                        <a class="nav-link" href="InicioServlet">Página Principal <span class="sr-only">(current)</span></a>
                    </li>   
                    <li class="nav-item">
                        <a class="nav-link" href="cart.jsp">Carrinho</a>
                    </li>
                    <%
                        Administrador administrador = (Administrador) session.getAttribute("administrador");
                        boolean isAdminLogged = (administrador != null && administrador instanceof Administrador);
                        if (isAdminLogged) {
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Administrador</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="ListarCliente">Clientes</a>
                            <a class="dropdown-item" href="ListarProduto">Produtos</a>
                            <a class="dropdown-item" href="ListarCategoria">Categorias</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="report.jsp">Relatórios</a>
                        </div>
                    </li>
                <%
                    }
                %>                
                </ul>      
                <form class="form-inline my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Procurar por..." aria-label="Search">
                    <button class="btn dark my-sm-0" type="submit" style="background-color: #E97568">Procurar</button>
                </form>
                <ul class="navbar-nav ml-auto mr-3">
                    <%
                        Cliente cliente = (Cliente) session.getAttribute("cliente");
                        boolean isClientLogged = (cliente != null && cliente instanceof Cliente);
                        if (isClientLogged) {
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Olá, <%= cliente.getNome()%></a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="MostrarClienteConta?id=<%= cliente.getId() %>">Minha conta</a>
                            <a class="dropdown-item" href="ListarCompraServlet">Minhas compras</a>
                        </div>
                    </li>                 
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Sair</a>
                    </li>
                    <%
                    } else if (isAdminLogged) {
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Olá, <%= administrador.getNome()%></a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="MostrarAdministradorServlet?id=<%= administrador.getId() %>">Minha conta</a>
                        </div>
                    </li>                
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">Sair</a>
                    </li>
                    <%
                        } else {
                    %>          
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Entrar</a>
                    </li>                
                    <li class="nav-item">
                        <a class="nav-link" href="signup.jsp" tabindex="-1" aria-disabled="true">Cadastrar-se</a>
                    </li>
                    <%
                        }
                    %>
                </ul>                
            </div>
        </nav>
        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null && mensagem.length() > 0) {
        %>
        <div class="container" style="color: #E97568"><%=mensagem%></div> 
        <%
            }
        %>
    </body>
</html>
