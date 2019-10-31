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
public class DeletarAdministradorServlet extends HttpServlet {

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
        AdministradorModel administradorModel = new AdministradorModel();
        /* Executa o método deletar que deleta um administrador existente na camada de persistência */
        boolean sucesso = administradorModel.deletar(id);
        /* Se o resultado da deleção for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na deleção */
            request.setAttribute("mensagem","Administrador deletado com sucesso");
        } else {
            /* Grava na requisição uma mensagem de falha na deleção */
            request.setAttribute("mensagem","Não foi possível deletar o administrador");
        }
        // Saída
        /* Despacha a requisição atual para a página inicial */
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
