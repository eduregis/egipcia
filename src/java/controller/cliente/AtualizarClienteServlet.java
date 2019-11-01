/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.administrador.Administrador;
import model.cliente.ClienteModel;

/**
 *
 * @author eduardo
 */
public class AtualizarClienteServlet extends HttpServlet {

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
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        // Processamento
        /* Instancia a classe de negocio que trata a requisição deste controle */
        ClienteModel clienteModel = new ClienteModel();
        /* Executa o método deletar que atualiza um cliente existente na camada de persistência */
        boolean sucesso = clienteModel.atualizar(id, nome, endereco, login, senha, email);
        /* Se o resultado da atualização for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na atualização */
            request.setAttribute("mensagem","Cliente atualizado com sucesso");
        } else {
            /* Grava na requisição uma mensagem de falha na atualização */
            request.setAttribute("mensagem","Não foi possível atualizar o cliente");
        }
        // Saída
        // Testa se quem está excluindo é o admin
        HttpSession session = request.getSession();
        Administrador admin = (Administrador)session.getAttribute("administrador");
        if(admin != null) {
            /* Direciona para o servlet de listar clientes, para atualizar a lista exibida com o valor novo inserido */
            request.getRequestDispatcher("ListarCliente").forward(request, response);
        } else {
            request.getRequestDispatcher("InicioServlet").forward(request, response);
        }
    }
}
