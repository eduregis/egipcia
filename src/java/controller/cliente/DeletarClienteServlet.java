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
public class DeletarClienteServlet extends HttpServlet {

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
        ClienteModel clienteModel = new ClienteModel();
        /* Executa o método deletar que deleta um cliente existente na camada de persistência */
        boolean sucesso = clienteModel.deletar(id);
        /* Se o resultado da deleção for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na deleção */
            request.setAttribute("mensagem","Cliente deletado com sucesso");
        } else {
            /* Grava na requisição uma mensagem de falha na deleção */
            request.setAttribute("mensagem","Não foi possível deletar o cliente");
        }
        // Saída
        // Testa se quem está excluindo é o admin
        HttpSession session = request.getSession();
        Administrador admin = (Administrador)session.getAttribute("administrador");
        if(admin != null) {
            /*Caso seja, direciona para o servlet de listar clientes, para atualizar a lista exibida com o valor novo inserido */
            request.getRequestDispatcher("ListarCliente").forward(request, response);
        } else {
             /*Caso não seja, a exclusão acontece e a sessão é encerrada Despacha a requisiç */
             request.getRequestDispatcher("Logout").forward(request, response);
        }
       
    }
}
