/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produto;

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
public class ProdutoDAO {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/egipcia-ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "123456";

    /**
     * Método utilizado para listar todos os produtos registradas
     *
     * @return
     */
    public List<Produto> listarProdutos() {
        List<Produto> resultado = new ArrayList<Produto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, descricao, preco, foto, quantidade FROM produtos");
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                resultado.add(produto);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Produto>();
        }
        return resultado;
    }

    /**
     * Método utilizado para listar um produto pelo id
     *
     * @param id
     * @return
     */
    public Produto listarProduto(int id) {
        Produto produto = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, descricao, preco, foto, quantidade FROM produtos WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("foto"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return produto;
    }

    /**
     * Método utilizado para inserir uma nova produto
     *
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @return
     */
    public boolean inserir(String descricao, double preco, String foto, int quantidade) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categorias (descricao, preco, foto, quantidade) VALUES (? ,? ,? ,?)");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setString(3, foto);
            preparedStatement.setInt(4, quantidade);
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
     * Método utilizado para alterar um produto já existente
     *
     * @param id
     * @param descricao
     * @param preco
     * @param foto
     * @param quantidade
     * @return
     */
    public boolean alterar(int id, String descricao, double preco, String foto, int quantidade) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET descricao = ?, preco = ?, foto = ?, quantidade = ? WHERE id = ?");
            preparedStatement.setString(1, descricao);
            preparedStatement.setDouble(2, preco);
            preparedStatement.setString(3, foto);
            preparedStatement.setInt(4, quantidade);
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
     * Método para deletar um produto já existente
     *
     * @param id
     * @return
     */
    public boolean deletar(int id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produtos WHERE id = ?");
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