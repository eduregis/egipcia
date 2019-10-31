/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cliente.ClienteModel;
import model.produto.ProdutoModel;

/**
 *
 * @author eduardo
 */
public class DeletarProdutoServlet extends HttpServlet {

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
        ProdutoModel produtoModel = new ProdutoModel();
        /* Executa o método deletar que deleta um produto existente na camada de persistência */
        boolean sucesso = produtoModel.deletar(id);
        /* Se o resultado da deleção for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na deleção */
            request.setAttribute("mensagem","Produto deletado com sucesso");
        } else {
            /* Grava na requisição uma mensagem de falha na deleção */
            request.setAttribute("mensagem","Não foi possível deletar o produto");
        }
        // Saída
        /* Despacha a requisição atual para o servlet ListarCliente no intuito de mostrar todas os produtos e mensagem gerada nesse servlet */
        request.getRequestDispatcher("ListarProduto").forward(request, response);
    }
}
