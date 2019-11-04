/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria.Categoria;
import model.categoria.CategoriaModel;
import model.produto.ProdutoModel;

/**
 *
 * @author Luís Eduardo
 */
public class CadastrarProdutoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // entrada
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        int qtd = Integer.parseInt(request.getParameter("qtd"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        String categorias = request.getParameter("categorias");
        // processamento
        List<Categoria> produtoCategorias = new ArrayList<>();
        if (categorias != null && categorias.trim().length() > 0) {
            String[] categoriasId = categorias.split(";");
            if (categoriasId == null) {
                categoriasId = new String[]{categorias};
            }
            CategoriaModel categoriaNegocio = new CategoriaModel();
            for (String categoriaId : categoriasId) {
                produtoCategorias.add(categoriaNegocio.listar(Integer.parseInt(categoriaId)));
            }
        }
        // processamento
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.inserir(nome, descricao, qtd, preco, produtoCategorias);
        // saída
        /* Direciona para o servlet de listar produtos, para atualizar a lista exibida com o valor novo inserido */
        request.getRequestDispatcher("ListarProduto").forward(request, response);
        // request.getRequestDispatcher("products.jsp").forward(request, response);
    }

}
