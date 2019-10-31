/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.administrador;

/**
 *
 * @author Luís Eduardo
 */
public class AdministradorModel {

    public Administrador verificarSessao(String login, String senha) {
        AdministradorDAO dao = new AdministradorDAO();
        return dao.listarAdministrador(login, senha);
    }
    
    public boolean inserir(String nome, String email, String login, String senha) {
        AdministradorDAO dao = new AdministradorDAO();
        return dao.inserir(nome, login, senha, email);
    }
    
    public Administrador listar(int id) {
        AdministradorDAO administradorDAO = new AdministradorDAO();
        return administradorDAO.listarAdministrador(id);
    }
    
    public boolean atualizar(int id, String nome, String login, String senha, String email){
        if (id > 0 && nome != null && nome.trim().length() > 0
                && login != null && login.trim().length() > 0
                && senha != null && senha.trim().length() > 0
                && email != null && email.trim().length() > 0){
            AdministradorDAO administradorDAO = new AdministradorDAO();
            return administradorDAO.alterar(id, nome, login, senha, email);
        } else {
            return false;
        }
    }
    
    public boolean deletar(int id){
        if (id > 0){
            AdministradorDAO administradorDAO = new AdministradorDAO();
            return administradorDAO.deletar(id);
        } else {
            return false;
        }
    }
}
