/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.relatorios;

import java.util.Date;

/**
 *
 * @author eduardo
 */
public class ValorRecebidoPorDia {
    private double valor;
    private Date dia;
    
    public ValorRecebidoPorDia(double valor, Date dia){
        this.valor = valor;
        this.dia = dia;
    }

    public ValorRecebidoPorDia() {
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }
    
    
}
