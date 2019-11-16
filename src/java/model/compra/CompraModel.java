/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compra;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Luís Eduardo
 */
public class CompraModel {

    public boolean inserir(int cliente_id, Timestamp data) {
        CompraDAO dao = new CompraDAO();
        return dao.inserir(cliente_id, data);
    }
    
}
