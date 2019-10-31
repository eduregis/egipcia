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
    
}
