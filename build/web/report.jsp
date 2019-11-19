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
            <div class="mt-3 ml-5 py-3">
                <div class="row">
                    <h2 style="color: #58889C">Total de compras por cliente</h2>
                </div>
                <div class="row">
                    <button onclick="document.location = 'RelatorioXLSTotalCompraPorClienteServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como planilha</button>
                    <button onclick="document.location = 'RelatorioDOCTotalCompraPorClienteServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como .doc</button>
                    <button onclick="document.location = 'RelatorioPDFTotalCompraPorClienteServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como pdf</button>
                </div>
                <hr>
                <div class="row">
                    <h2 style="color: #58889C">Produtos em falta no estoque</h2>
                </div>
                <div class="row">
                    <button onclick="document.location = 'RelatorioXLSProdutoForaDeEstoqueServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como planilha</button>
                    <button onclick="document.location = 'RelatorioDOCProdutoForaDeEstoqueServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como .doc</button>
                    <button onclick="document.location = 'RelatorioPDFProdutoForaDeEstoqueServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como pdf</button>
                </div>
                <hr>
                <div class="row">
                    <h2 style="color: #58889C">Total de valor recebido por dia</h2>
                </div>
                <div class="row">
                    <button onclick="document.location = 'RelatorioXLSTotalRecebidoPorDiaServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como planilha</button>
                    <button onclick="document.location = 'RelatorioDOCValorRecebidoPorDiaServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como .doc</button>
                    <button onclick="document.location = 'RelatorioPDFTotalRecebidoPorDiaServlet';" class="btn dark m-3" style="background-color: #E97568">Baixar como pdf</button>
                </div>
                <hr>
            </div>
        </div>
    </body>
</html>