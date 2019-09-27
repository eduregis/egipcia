/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luís Eduardo
 *
 * Classe que representa os acessos aos dados de compras persistidas em um
 * banco de dados relacional
 */
public class CompraDAO {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/egipcia-ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "123456";

    /**
     * Método utilizado para listar todos as compras registradas
     *
     * @return
     */
    public List<Compra> listarCompras() {
        List<Compra> resultado = new ArrayList<Compra>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, cliente_id, data FROM compras");
            while (resultSet.next()) {
                Compra compra = new Compra();
                compra.setId(resultSet.getInt("id"));
                compra.setCliente_id(resultSet.getInt("cliente_id"));
                compra.setData(resultSet.getDate("data"));
                resultado.add(compra);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Compra>();
        }
        return resultado;
    }

    /**
     * Método utilizado para listar uma compra pelo id
     *
     * @param id
     * @return
     */
    public Compra listarCompra(int id) {
        Compra compra = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, cliente_id, data FROM compras WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                compra = new Compra();
                compra.setId(resultSet.getInt("id"));
                compra.setCliente_id(resultSet.getInt("cliente_id"));
                compra.setData(resultSet.getDate("data"));              
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return compra;
    }

    /**
     * Método utilizado para inserir uma nova compra
     *
     * @param cliente_id
     * @param data
     * @return
     */
    public boolean inserir(int cliente_id, Date data) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO compras (cliente_id, data) VALUES (?, ?)");
            preparedStatement.setInt(1, cliente_id);
            preparedStatement.setDate(2, data);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar uma compra já existente
     *
     * @param id
     * @param cliente_id
     * @param data
     * @return
     */
    public boolean alterar(int id, int cliente_id, Date data) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE compras SET cliente_id = ?, data = ? WHERE id = ?");
            preparedStatement.setInt(1, cliente_id);
            preparedStatement.setDate(2, data);
            preparedStatement.setInt(3, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para deletar uma compra já existente
     *
     * @param id
     * @return
     */
    public boolean deletar(int id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM compras WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
}