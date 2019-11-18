/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compra;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Luís Eduardo
 */
public class CompraModel {

    public int obterId() {
        CompraDAO dao = new CompraDAO();
        return dao.obterId();
    }
    
    public boolean inserir(int compra_id, int cliente_id, Timestamp data) {
        CompraDAO dao = new CompraDAO();
        return dao.inserir(compra_id, cliente_id, data);
    }
    
    public List<Compra> listar() {
        CompraDAO compraDAO = new CompraDAO();
        return compraDAO.listarCompras();
    }
    
    public List<Compra> listar(int id) {
        CompraDAO compraDAO = new CompraDAO();
        return compraDAO.listarCompras(id);
    }
    
    public Compra listar(int id, Timestamp data_compra) {
        CompraDAO compraDAO = new CompraDAO();
        return compraDAO.listarCompra(id, data_compra);
    }
    
    public boolean deletar(int id){
        if (id > 0){
            CompraDAO clienteDAO = new CompraDAO();
            return clienteDAO.deletar(id);
        } else {
            return false;
        }
    }
    
}
