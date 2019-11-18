/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compra;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.carrinhocompra.CarrinhoCompraModel;
import model.cookie.CookieUtils;
import javax.servlet.http.HttpSession;
import model.administrador.Administrador;
import model.carrinhocompra.CarrinhoCompraItem;
import model.cliente.Cliente;
import model.cliente.ClienteModel;
import model.compra.Compra;
import model.compra.CompraModel;
import model.compraProduto.CompraProdutoModel;
import model.produto.ProdutoModel;

/**
 *
 * @author Luís Eduardo
 */
public class CadastrarCompraServlet extends HttpServlet {

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
        // Testa se quem está excluindo é o admin
        HttpSession session = request.getSession();
        Cliente c = (Cliente)session.getAttribute("cliente");
        /* Checa se o usuário está logdo numa sessão válida de cliente */
        if(c != null) {
            // chama o cookie com o carrinho de compras, se tiver
            Cookie ck = CookieUtils.obterCookie(request);
            if (ck != null) {
                CompraModel compraModel = new CompraModel();
                int id = compraModel.obterId();
                // Pega a data atual para fazer a compra
                Timestamp dataCompra = new Timestamp(System.currentTimeMillis());
                // Inserindo tupla que representa a compra
                compraModel.inserir(id, c.getId(), dataCompra);
                // IMPORTANTE!!!
                // Tentando pegar o id da compra recém criada, nas linhas acima
                Compra compra = compraModel.listar(c.getId(), dataCompra);
                List<CarrinhoCompraItem> carrinhoCompra = CarrinhoCompraModel.obterCarrinhoCompra(ck.getValue());
                CompraProdutoModel compraProdutoModel = new CompraProdutoModel();
                ProdutoModel produtoModel = new ProdutoModel();
                for (int i = 0; i < carrinhoCompra.size(); i++) {
                    CarrinhoCompraItem cci = carrinhoCompra.get(i);
                    // a linha abaixo deve ser o código final
                    // compraProdutoModel.inserir(compra.getId(), cci.getProduto().getId(), cci.getQuantidade());
                    // substituto temporário
                    produtoModel.atualizarEstoque(cci.getProduto().getId(), cci.getQuantidade());
                    compraProdutoModel.inserir(id, cci.getProduto().getId(), cci.getQuantidade());
                }
                // limpa o cookie do carrinho de compras
                ck.setValue("");
                response.addCookie(ck);
                request.setAttribute("mensagem", "A compra foi realizada com sucesso!");
                request.getRequestDispatcher("InicioServlet").forward(request, response);                
            } else {
                request.setAttribute("mensagem", "O carrinho ainda está vazio!");
                request.getRequestDispatcher("InicioServlet").forward(request, response);
            }
        } else {
            request.setAttribute("mensagem", "Entre com uma sessão válida de cliente para efetuar a compra!");
            request.getRequestDispatcher("InicioServlet").forward(request, response);
        }
        
    }

}
