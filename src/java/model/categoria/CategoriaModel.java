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
    
    public Categoria listar(int id) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.listarCategoria(id);
    }
    
    public boolean atualizar(int id, String descricao){
        if (id > 0 && descricao != null && descricao.trim().length() > 0){
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            return categoriaDAO.alterar(id, descricao);
        } else {
            return false;
        }
    }
    
    public boolean deletar(int id){
        if (id > 0){
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            return categoriaDAO.deletar(id);
        } else {
            return false;
        }
    }
}
