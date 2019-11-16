/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compra;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.cliente.Cliente;
import model.cliente.ClienteModel;
import model.compra.Compra;
import model.compra.CompraModel;

/**
 *
 * @author eduardo
 */
public class ListarCompraServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Cliente c = (Cliente)session.getAttribute("cliente");
        /* Instancia a classe de negócio que trata a requisição deste controle */
        CompraModel compraModel = new CompraModel();
        /* Executa o método listar que gera uma lista com todas os clientes cadastrados */
        List<Compra> compras = compraModel.listar(c.getId());
        /* Grava a lista com todos os clientes cadastrados no objeto que representa a requisição */
        request.setAttribute("compras", compras);
        // saída
        /* Despacha a requisição atual para a página clients.jsp no intuito de mostra todas os clientes recuperadas e gravadas na requisição */
        request.getRequestDispatcher("myShopping.jsp").forward(request, response);
    }
}
