/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria.Categoria;
import model.categoria.CategoriaModel;
import model.produto.Produto;
import model.produto.ProdutoModel;

/**
 *
 * @author eduardo
 */
public class BuscarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
            ProdutoModel produtoModel = new ProdutoModel();
        /* Executa o método listar que gera uma lista com todas os produtos cadastrados */
        List<Produto> produtos = produtoModel.listarPorBusca(nome);
        /* Grava a lista com todos os produtos cadastrados no objeto que representa a requisição */
        request.setAttribute("produtos", produtos);
        request.setAttribute("nome", nome);
        
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
