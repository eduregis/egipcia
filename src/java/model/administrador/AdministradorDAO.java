/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.administrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lúis Eduardo
 *
 * Classe que representa os acessos aos dados de administradores persistidas em um
 * banco de dados relacional
 */
public class AdministradorDAO {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/egipcia-ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "123456";

    /**
     * Método utilizado para listar todos os administradores registrados
     *
     * @return
     */
    public List<Administrador> listarAdministradores() {
        List<Administrador> resultado = new ArrayList<Administrador>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome, login, senha, email FROM administradores");
            while (resultSet.next()) {
                Administrador administrador = new Administrador();
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setLogin(resultSet.getString("login"));
                administrador.setSenha(resultSet.getString("senha"));
                administrador.setSenha(resultSet.getString("email"));
                resultado.add(administrador);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Administrador>();
        }
        return resultado;
    }

    /**
     * Método utilizado para listar um administrador pelo id
     *
     * @param id
     * @return
     */
    public Administrador listarAdministrador(int id) {
        Administrador administrador = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, nome, login, senha, email FROM administradores WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                administrador = new Administrador();
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setLogin(resultSet.getString("login"));
                administrador.setSenha(resultSet.getString("senha"));
                administrador.setEmail(resultSet.getString("email"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return administrador;
    }

    /**
     * Método utilizado para listar um administrador pelo login e senha
     *
     * @param login
     * @param senha
     * @return
     */
    public Administrador listarAdministrador(String login, String senha) {
        Administrador administrador = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, nome, login, senha, email FROM administradores WHERE login = ? AND senha = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                administrador = new Administrador();
                administrador.setId(resultSet.getInt("id"));
                administrador.setNome(resultSet.getString("nome"));
                administrador.setLogin(resultSet.getString("login"));
                administrador.setSenha(resultSet.getString("senha"));
                administrador.setEmail(resultSet.getString("email"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return administrador;
    }

    /**
     * Método utilizado para inserir um novo administrador
     *
     * @param nome
     * @param login
     * @param senha
     * @param email
     * @return
     */
    public boolean inserir(String nome, String login, String senha, String email) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO administradores (nome, login, senha, email) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, senha);
            preparedStatement.setString(4, email);
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
     * Método utilizado para alterar um administrador já existente
     *
     * @param id
     * @param nome
     * @param login
     * @param senha
     * @param email
     * @return
     */
    public boolean alterar(int id, String nome, String login, String senha, String email) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE administradores SET nome = ?, login = ?, senha = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, senha);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para deletar um administrador já existente
     *
     * @param id
     * @return
     */
    public boolean deletar(int id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM administradores WHERE id = ?");
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