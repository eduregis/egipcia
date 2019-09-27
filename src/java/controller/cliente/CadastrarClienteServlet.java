/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cliente.ClienteModel;

/**
 *
 * @author Luís Eduardo
 */
public class CadastrarClienteServlet extends HttpServlet {

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
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // processamento
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.inserir(nome, endereco, email, login, senha);
        // saída
        /* Direciona para o servlet de listar clientes, para atualizar a lista exibida com o valor novo inserido */
        request.getRequestDispatcher("ListarCliente").forward(request, response);
        // request.getRequestDispatcher("clients.jsp").forward(request, response);
    }

}
