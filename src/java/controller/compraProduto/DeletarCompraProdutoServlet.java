/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compraProduto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.compraProduto.CompraProdutoModel;

/**
 *
 * @author eduardo
 */
public class DeletarCompraProdutoServlet extends HttpServlet {

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
        int compra_id = Integer.parseInt(request.getParameter("compra_id"));
        int produto_id = Integer.parseInt(request.getParameter("produto_id"));
        // Processamento
        /* Instancia a classe de negocio que trata a requisição deste controle */
        CompraProdutoModel compraProdutoModel = new CompraProdutoModel();
        /* Executa o método deletar que deleta um produto existente na camada de persistência */
        boolean sucesso = compraProdutoModel.deletar(compra_id, produto_id);
        /* Se o resultado da deleção for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na deleção */
            request.setAttribute("mensagem","Compra deletada com sucesso!");
        } else {
            /* Grava na requisição uma mensagem de falha na deleção */
            request.setAttribute("mensagem","Não foi possível deletar a compra!");
        }
        // Saída
        /* Despacha a requisição atual para o servlet ListarCliente no intuito de mostrar todas os produtos e mensagem gerada nesse servlet */
        request.getRequestDispatcher("shopping.jsp").forward(request, response);
    }
}
