/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compraProduto;

import java.util.List;

/**
 *
 * @author Luís Eduardo
 */
public class CompraProdutoModel {

    public boolean inserir(int compra_id, int produto_id, int quantidade) {
        CompraProdutoDAO dao = new CompraProdutoDAO();
        return dao.inserir(compra_id, produto_id, quantidade);
    }
    
    public List<CompraProduto> listar(int id) {
        CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
        return compraProdutoDAO.listarCompraProdutoPorCompra_id(id);
    }
    
    public boolean deletar(int compra_id, int produto_id){
        if (compra_id > 0 && produto_id > 0){
            CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
            return compraProdutoDAO.deletar(compra_id, produto_id);
        } else {
            return false;
        }
    }
}
