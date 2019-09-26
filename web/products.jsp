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
                    <hr>
                </div>
            </div>            
            <div class="row pb-3"> 
                <div class="collapse multi-collapse" id="createClientDiv">
                    <div class="row pb-3">
                        <div class="col-12 mx-5">
                            <form style="color: #58889C">
                                <div class="form-group">
                                    <label for="inputName">Nome do Produto</label>
                                    <input type="text" class="form-control" id="inputName" placeholder="Insira o nome do produto...">
                                </div>
                                <div class="form-group">
                                    <label for="inputDesc">DescriÃ§Ã£o do Produto</label>
                                    <textarea class="form-control" id="inputDesc" rows="3" placeholder="Insira a descriÃ§Ã£o do produto..."></textarea>
                                </div>
                                <div class="form-row">                                           
                                    <div class="form-group col-md-6">
                                        <label for="inputQtd">Quantidade no estoque:</label>
                                        <input type="number" class="form-control" id="inputQtd">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputPrice">PreÃ§o (unidade):</label>
                                        <input type="text" class="form-control" id="inputAddress" placeholder="Insira o preÃ§o...">
                                    </div>
                                </div>                                        
                                <div class="form-row">
                                    <label for="inputImage">Imagem</label>
                                    <input type="text" class="form-control" id="inputImage" placeholder="Insira o endereÃ§o da foto...">
                                </div> 
                                <div class="form-row">
                                    <label for="inputCategories">Categorias</label>
                                    <select id="inputCategories" class="form-control">
                                        <option>Categoria 1</option>
                                        <option>Categoria 2</option>
                                        <option>Categoria 3</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn dark mt-3 mb-5 sm-0" type="submit" style="background-color: #E97568">Cadastrar</button>
                            </form>
                        </div>                            
                    </div>
                </div>
            </div>
            <div class="ml-3">
                <div>                    
                    <button class="btn dropdown-toggle dark my-sm-0" type="button" style="background-color: #E97568" data-toggle="collapse" data-target="#listClientDiv" aria-expanded="false" aria-controls="multiCollapseExample2">Listar produtos</button>
                    <hr>
                </div>
            </div>
            <div class="row pb-3">              
                <div class="collapse multi-collapse" id="listClientDiv">
                    <div class="row pb-3">
                        <div class="mx-5">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">id</th>
                                        <th scope="col">Nome do Produto</th>
                                        <th scope="col">DescriÃ§Ã£o</th>
                                        <th scope="col">Quantidade</th>
                                        <th scope="col">PreÃ§o unitÃ¡rio</th>
                                        <th scope="col">EndereÃ§o de imagem</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>#58889C</td>
                                        <td>Produto 1</td>
                                        <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum quia architecto, facere nostrum magnam ipsa cumque, sed nesciunt quaerat rerum veritatis perferendis. Exercitationem reiciendis quisquam dolor delectus cumque ullam nihil.</td>
                                        <td>2</td>
                                        <td>9,99</td>
                                        <td>Fortaleza, CearÃ¡, Brasil</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>#E97568</td>
                                        <td>Produto 2</td>
                                        <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum quia architecto, facere nostrum magnam ipsa cumque, sed nesciunt quaerat rerum veritatis perferendis. Exercitationem reiciendis quisquam dolor delectus cumque ullam nihil.</td>
                                        <td>2</td>
                                        <td>9,99</td>
                                        <td>Fortaleza, CearÃ¡, Brasil</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>#F4E19F</td>
                                        <td>Produto 3</td>
                                        <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum quia architecto, facere nostrum magnam ipsa cumque, sed nesciunt quaerat rerum veritatis perferendis. Exercitationem reiciendis quisquam dolor delectus cumque ullam nihil.</td>
                                        <td>2</td>
                                        <td>9,99</td>
                                        <td>Fortaleza, CearÃ¡, Brasil</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>                            
                    </div>    
                </div>
            </div>
        </div>
    </body>
</html>