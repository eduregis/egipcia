/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.categoria;

import java.sql.Connection;
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
 * Classe que representa os acessos aos dados de categorias persistidas em um
 * banco de dados relacional
 */
public class CategoriaDAO {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/egipcia-ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "123456";

    /**
     * Método utilizado para listar todos as categorias registradas
     *
     * @return
     */
    public List<Categoria> listarCategorias() {
        List<Categoria> resultado = new ArrayList<Categoria>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, descricao FROM categoria");
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setDescricao(resultSet.getString("descricao"));
                resultado.add(categoria);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Categoria>();
        }
        return resultado;
    }

    /**
     * Método utilizado para listar uma categoria pelo id
     *
     * @param id
     * @return
     */
    public Categoria listarCategoria(int id) {
        Categoria categoria = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, descricao FROM categorias WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setDescricao(resultSet.getString("descricao"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return categoria;
    }

    /**
     * Método utilizado para inserir uma novo categoria
     *
     * @param descricao
     * @return
     */
    public boolean inserir(String descricao) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categorias (descricao) VALUES (?)");
            preparedStatement.setString(1, descricao);
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
     * Método utilizado para alterar uma categoria já existente
     *
     * @param id
     * @param descricao
     * @return
     */
    public boolean alterar(int id, String descricao) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categorias SET descricao = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setInt(2, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para deletar uma categoria já existente
     *
     * @param id
     * @return
     */
    public boolean deletar(int id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categorias WHERE id = ?");
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