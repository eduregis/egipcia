/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produtoCategoria;

import java.util.List;

/**
 *
 * @author Luís Eduardo
 */
public class ProdutoCategoriaModel {

    public boolean inserir(int produto_id, int categoria_id) {
        ProdutoCategoriaDAO dao = new ProdutoCategoriaDAO();
        return dao.inserir(produto_id, categoria_id);
    }
    
    public List<ProdutoCategoria> listar(int id) {
        ProdutoCategoriaDAO produtoCategoriaDAO = new ProdutoCategoriaDAO();
        return produtoCategoriaDAO.listarProdutoCategoriasPorProduto_id(id);
    }
}
