/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.categoria;

import java.util.List;

/**
 *
 * @author Luís Eduardo
 */
public class CategoriaModel {

    public boolean inserir(String descricao) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.inserir(descricao);
    }
    
    public List<Categoria> listar() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.listarCategorias();
    }
    
}
