/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cliente;

import java.util.List;

/**
 *
 * @author Luís Eduardo
 */
public class ClienteModel {

    public Cliente verificarSessao(String login, String senha) {
        ClienteDAO dao = new ClienteDAO();
        return dao.listarCliente(login, senha);
    }
    
    public boolean inserir(String nome, String endereco, String login, String senha, String email) {
        ClienteDAO dao = new ClienteDAO();
        return dao.inserir(nome, endereco, login, senha, email);
    }
    
    public List<Cliente> listar() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.listarClientes();
    }
    
    public Cliente listar(int id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.listarCliente(id);
    }
    
    public boolean atualizar(int id, String nome, String endereco, String login, String senha, String email){
        if (id > 0 && nome != null && nome.trim().length() > 0
                && endereco != null && endereco.trim().length() > 0
                && login != null && login.trim().length() > 0
                && senha != null && senha.trim().length() > 0
                && email != null && email.trim().length() > 0){
            ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.alterar(id, nome, endereco, login, senha, email);
        } else {
            return false;
        }
    }
    
    public boolean deletar(int id){
        if (id > 0){
            ClienteDAO clienteDAO = new ClienteDAO();
            return clienteDAO.deletar(id);
        } else {
            return false;
        }
    }
}
