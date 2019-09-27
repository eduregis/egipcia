/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cliente.Cliente;
import model.cliente.ClienteModel;

/**
 *
 * @author eduardo
 */
public class ListarClienteServlet extends HttpServlet {

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
        ClienteModel clienteModel = new ClienteModel();
        /* Executa o método listar que gera uma lista com todas os clientes cadastrados */
        List<Cliente> clientes = clienteModel.listar();
        /* Grava a lista com todos os clientes cadastrados no objeto que representa a requisição */
        request.setAttribute("clientes", clientes);
        // saída
        /* Despacha a requisição atual para a página clients.jsp no intuito de mostra todas os clientes recuperadas e gravadas na requisição */
        request.getRequestDispatcher("clients.jsp").forward(request, response);
    }
}
