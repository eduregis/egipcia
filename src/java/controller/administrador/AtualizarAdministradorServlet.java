/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.administrador.AdministradorModel;
import model.cliente.ClienteModel;

/**
 *
 * @author eduardo
 */
public class AtualizarAdministradorServlet extends HttpServlet {

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
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // Processamento
        /* Instancia a classe de negocio que trata a requisição deste controle */
        AdministradorModel administradorModel = new AdministradorModel();
        /* Executa o método deletar que atualiza um administrador existente na camada de persistência */
        boolean sucesso = administradorModel.atualizar(id, nome, login, senha, email);
        /* Se o resultado da atualização for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na atualização */
            request.setAttribute("mensagem","Administrador atualizado com sucesso");
        } else {
            /* Grava na requisição uma mensagem de falha na atualização */
            request.setAttribute("mensagem","Não foi possível atualizar o administrador");
        }
        // Saída
        /* Despacha a requisição atual para a página principal */
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
