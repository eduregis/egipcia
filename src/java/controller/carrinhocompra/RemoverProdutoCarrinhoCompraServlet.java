/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.carrinhocompra;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.carrinhocompra.CarrinhoCompraModel;
import model.cookie.CookieUtils;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Servlet de controle para a ação de remover um produto no carrinho de compra
 */
public class RemoverProdutoCarrinhoCompraServlet extends HttpServlet {

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
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));

        Cookie c = CookieUtils.obterCookie(request);
        String novoValor = CarrinhoCompraModel.removerItem(produtoId, c.getValue());
        c.setValue(novoValor);
        response.addCookie(c);
        
        request.setAttribute("mensagem", "O produto foi removido do carrinho!");

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

}
