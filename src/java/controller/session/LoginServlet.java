/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.administrador.Administrador;
import model.administrador.AdministradorModel;
import model.cliente.Cliente;
import model.cliente.ClienteModel;

/**
 *
 * @author leoomoreira
 */
public class LoginServlet extends HttpServlet {

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
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // processamento
        ClienteModel clienteModel = new ClienteModel();
        Cliente cliente = clienteModel.verificarSessao(login, senha);
        AdministradorModel administradorModel = new AdministradorModel();
        Administrador administrador = administradorModel.verificarSessao(login, senha);
        // saída
        if (administrador == null) {
            if (cliente == null) {
            // errou o login ou senha
            request.setAttribute("mensagem", "Login ou senha incorreta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // acertou o login e a senha
                HttpSession session = request.getSession();
                session.setAttribute("cliente", cliente);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            request.setAttribute("mensagem", "Login ou senha incorreta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // acertou o login e a senha
            HttpSession session = request.getSession();
            session.setAttribute("administrador", administrador);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }

}
