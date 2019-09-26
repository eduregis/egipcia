/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produto;

/**
 *
 * @author Luís Eduardo
 */
public class ProdutoModel {

    public boolean inserir(String descricao, double preco, String foto, int quantidade) {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.inserir(descricao, preco, foto, quantidade);
    }
    
}
