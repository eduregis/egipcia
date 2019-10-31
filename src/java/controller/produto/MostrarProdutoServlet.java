/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.produto.Produto;
import model.produto.ProdutoModel;

/**
 *
 * @author eduardo
 */
public class MostrarProdutoServlet extends HttpServlet {

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
        // Entrada
        int id = Integer.parseInt(request.getParameter("id"));
        // Processamento
        /* Instancia a classe de negocio que trata a requisição deste controle */
        ProdutoModel produtoModel = new ProdutoModel();
        /* Executa o método listar que recupera um produto pelo identificador */
        Produto produto = produtoModel.listar(id);
        /* Grava o produo cadastrado no objeto que representa à requisição */
        request.setAttribute("produto",produto);
        // Saída
        /* Despacha a requisição atual para o página de atualização do produto escolhido e mensagem gerada nesse servlet */
        request.getRequestDispatcher("alterProduct.jsp").forward(request, response);
    }
}
