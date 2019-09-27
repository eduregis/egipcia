/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.produtoCategoria;

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
 * Classe que representa os acessos aos dados relacionados de produtos e categorias persistidas em um
 * banco de dados relacional
 */
public class ProdutoCategoriaDAO {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/egipcia-ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "123456";

    /**
     * Método utilizado para listar todos as produtoCategorias registradas
     *
     * @return
     */
    public List<ProdutoCategoria> listarProdutoCategorias() {
        List<ProdutoCategoria> resultado = new ArrayList<ProdutoCategoria>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT produto_id, categoria_id FROM produtoCategorias");
            while (resultSet.next()) {
                ProdutoCategoria produtoCategoria = new ProdutoCategoria();
                produtoCategoria.setProduto_id(resultSet.getInt("produto_id"));
                produtoCategoria.setCategoria_id(resultSet.getInt("categoria_id"));
                resultado.add(produtoCategoria);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<ProdutoCategoria>();
        }
        return resultado;
    }

    /**
     * Método utilizado para listar uma produtoCategorias pelo id do produto
     *
     * @param produto_id
     * @return
     */
    public ProdutoCategoria listarProdutoCategoriaPorProduto_id(int produto_id) {
        ProdutoCategoria produtoCategoria = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT produto_id, categoria_id FROM produtoCategorias WHERE produto_id = ?");
            preparedStatement.setInt(1, produto_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produtoCategoria = new ProdutoCategoria();
                produtoCategoria.setProduto_id(resultSet.getInt("produto_id"));
                produtoCategoria.setCategoria_id(resultSet.getInt("categoria_id"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return produtoCategoria;
    }
    
    /**
     * Método utilizado para listar uma produtoCategorias pelo id da categoria
     *
     * @param categoria_id
     * @return
     */
    public ProdutoCategoria listarProdutoCategoriaPorCategoria_id(int categoria_id) {
        ProdutoCategoria produtoCategoria = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT produto_id, categoria_id FROM produtoCategorias WHERE categoria_id = ?");
            preparedStatement.setInt(1, categoria_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produtoCategoria = new ProdutoCategoria();
                produtoCategoria.setProduto_id(resultSet.getInt("produto_id"));
                produtoCategoria.setCategoria_id(resultSet.getInt("categoria_id"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return produtoCategoria;
    }
    
    /**
     * Método utilizado para inserir uma nova compraProduto
     *
     * @param produto_id
     * @param categoria_id
     * @return
     */
    public boolean inserir(int produto_id, int categoria_id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtoCategorias (produto_id, categoria_id) VALUES (?, ?)");
            preparedStatement.setInt(1, produto_id);
            preparedStatement.setInt(2, categoria_id);
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
     * Método utilizado para alterar uma produtoCategoria já existente
     *
     * @param produto_id
     * @param categoria_id
     * Não consigo ver motivo para o alterar nesse caso
     * @return
     */
    
    /**
     * Método para deletar um produtoCategoria já existente, utilizando as 2 ids
     *
     * @param produto_id
     * @param categoria_id
     * @return
     */
    public boolean deletar(int produto_id, int categoria_id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produtoCategorias WHERE produto_id = ? AND categoria_id = ?");
            preparedStatement.setInt(1, produto_id);
            preparedStatement.setInt(2, categoria_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
}