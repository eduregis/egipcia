/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria.CategoriaModel;

/**
 *
 * @author Luís Eduardo
 */
public class CadastrarCategoriaServlet extends HttpServlet {

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
        String descricao = request.getParameter("descricao");
        // processamento
        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.inserir(descricao);
        // saída
        /* Direciona para o servlet de listar categorias, para atualizar a lista exibida com o valor novo inserido */
        request.getRequestDispatcher("ListarCategoria").forward(request, response);
        // request.getRequestDispatcher("categories.jsp").forward(request, response);
    }

}
