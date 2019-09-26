/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.categoria;

/**
 *
 * @author Luís Eduardo
 */
public class CategoriaModel {

    public boolean inserir(String descricao) {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.inserir(descricao);
    }
    
}
