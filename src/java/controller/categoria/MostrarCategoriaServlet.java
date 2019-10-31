/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.categoria;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria.Categoria;
import model.categoria.CategoriaModel;

/**
 *
 * @author eduardo
 */
public class MostrarCategoriaServlet extends HttpServlet {

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
        CategoriaModel categoriaModel = new CategoriaModel();
        /* Executa o método listar que recupera uma categoria pelo identificador */
        Categoria categoria = categoriaModel.listar(id);
        /* Grava a categoria cadastrada no objeto que representa à requisição */
        request.setAttribute("categoria",categoria);
        // Saída
        /* Despacha a requisição atual para o servlet ListarCategoria no intuito de mostrar todas as categorias e mensagem gerada nesse servlet */
        request.getRequestDispatcher("alterCategorie.jsp").forward(request, response);
    }
}
