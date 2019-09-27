/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.categoria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class ListarCategoriaServlet extends HttpServlet {

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
        // processamento
        /* Instancia a classe de negócio que trata a requisição deste controle */
        CategoriaModel categoriaModel = new CategoriaModel();
        /* Executa o método listar que gera uma lista com todas as categorias cadastradas */
        List<Categoria> categorias = categoriaModel.listar();
        /* Grava a lista com todas as categorias cadastradas no objeto que representa a requisição */
        request.setAttribute("categorias", categorias);
        // saída
        /* Despacha a requisição atual para a página categories.jsp no intuito de mostra todas as categorias recuperadas e gravadas na requisição */
        request.getRequestDispatcher("categories.jsp").forward(request, response);
    }
}
