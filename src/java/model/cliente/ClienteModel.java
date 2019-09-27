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
    
    public boolean inserir(String nome, String endereco, String email, String login, String senha) {
        ClienteDAO dao = new ClienteDAO();
        return dao.inserir(nome, endereco, login, senha, email);
    }
    
    public List<Cliente> listar() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.listarClientes();
    }
}
