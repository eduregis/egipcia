/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produto;

import java.util.List;
import model.categoria.Categoria;

/**
 *
 * @author Luís Eduardo
 */
public class ProdutoModel {

    public boolean inserir(String nome, String descricao, int quantidade, double preco, List<Categoria> categorias) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.inserir(nome, descricao, quantidade, preco, categorias);
    }
    
    public List<Produto> listar() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.listarProdutos();
    }
    
    public Produto listar(int id) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.listarProduto(id);
    }
    
    public boolean alterarFoto(int id, String foto) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.alterarFoto(id, foto);
    }
    
    public boolean atualizar(int id, String nome, String descricao, int qtd, double preco, String imagem, List<Categoria> categorias){
        if (id > 0 && nome != null && nome.trim().length() > 0
                && descricao != null && descricao.trim().length() > 0
                && imagem != null && imagem.trim().length() > 0){
            ProdutoDAO produtoDAO = new ProdutoDAO();
            return produtoDAO.alterar(id, nome, descricao, qtd, preco, categorias);
        } else {
            return false;
        }
    }
    
    public boolean deletar(int id){
        if (id > 0){
            ProdutoDAO produtoDAO = new ProdutoDAO();
            return produtoDAO.deletar(id);
        } else {
            return false;
        }
    }
}
