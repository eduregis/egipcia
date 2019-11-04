/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria.Categoria;
import model.categoria.CategoriaModel;
import model.produto.ProdutoModel;

/**
 *
 * @author eduardo
 */
public class AtualizarProdutoServlet extends HttpServlet {

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
        String descricao = request.getParameter("descricao");
        int qtd = Integer.parseInt(request.getParameter("qtd"));
        double preco = Double.parseDouble(request.getParameter("preco"));
        String imagem = request.getParameter("imagem");
        String categorias = request.getParameter("categorias");
        // processamento
        List<Categoria> produtoCategorias = new ArrayList<>();
        if (categorias != null && categorias.trim().length() > 0) {
            String[] categoriasId = categorias.split(";");
            if (categoriasId == null) {
                categoriasId = new String[]{categorias};
            }
            CategoriaModel categoriaNegocio = new CategoriaModel();
            for (String categoriaId : categoriasId) {
                produtoCategorias.add(categoriaNegocio.listar(Integer.parseInt(categoriaId)));
            }
        }
        // Processamento
        /* Instancia a classe de negocio que trata a requisição deste controle */
        ProdutoModel produtoModel = new ProdutoModel();
        /* Executa o método deletar que atualiza um cliente existente na camada de persistência */
        boolean sucesso = produtoModel.atualizar(id, nome, descricao, qtd, preco, imagem, produtoCategorias);
        /* Se o resultado da atualização for sucesso */
        if (sucesso){
            /* Grava na requisição uma mensagem de sucesso na atualização */
            request.setAttribute("mensagem","Produto atualizado com sucesso");
        } else {
            /* Grava na requisição uma mensagem de falha na atualização */
            request.setAttribute("mensagem","Não foi possível atualizar o produto");
        }
        // Saída
        /* Despacha a requisição atual para o servlet ListarProduto no intuito de mostrar todas os produtos e mensagem gerada nesse servlet */
        request.getRequestDispatcher("ListarProduto").forward(request, response);
    }
}
