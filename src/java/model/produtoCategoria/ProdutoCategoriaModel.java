/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produtoCategoria;

/**
 *
 * @author Luís Eduardo
 */
public class ProdutoCategoriaModel {

    public boolean inserir(int produto_id, int categoria_id) {
        ProdutoCategoriaDAO dao = new ProdutoCategoriaDAO();
        return dao.inserir(produto_id, categoria_id);
    }
    
}
