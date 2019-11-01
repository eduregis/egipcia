/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.produto.Produto;
import model.produto.ProdutoModel;
/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet que faz o controle da inicialização da página principal da aplicação
 */
public class InicioServlet extends HttpServlet {

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
         ProdutoModel produtoModel = new ProdutoModel();
        /* Executa o método listar que gera uma lista com todas os produtos cadastrados */
        List<Produto> produtos = produtoModel.listar();
        /* Grava a lista com todos os produtos cadastrados no objeto que representa a requisição */
        request.setAttribute("produtos", produtos);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
