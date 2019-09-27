/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compraProduto;

/**
 *
 * @author Luís Eduardo
 */
public class CompraProdutoModel {

    public boolean inserir(int compra_id, int produto_id, int quantidade) {
        CompraProdutoDAO dao = new CompraProdutoDAO();
        return dao.inserir(compra_id, produto_id, quantidade);
    }
    
}
