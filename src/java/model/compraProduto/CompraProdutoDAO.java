/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.compraProduto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.relatorios.ValorRecebidoPorDia;

/**
 *
 * @author Luís Eduardo
 *
 * Classe que representa os acessos aos dados relacionados de compras e produtos persistidas em um
 * banco de dados relacional
 */
public class CompraProdutoDAO {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/egipcia-ecommerce";
    private static final String JDBC_USUARIO = "postgres";
    private static final String JDBC_SENHA = "123456";

    /**
     * Método utilizado para listar todos as compraProdutos registradas
     *
     * @return
     */
    public List<CompraProduto> listarCompraProdutos() {
        List<CompraProduto> resultado = new ArrayList<CompraProduto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT compra_id, produto_id, qtd FROM compras_produtos");
            while (resultSet.next()) {
                CompraProduto compraProduto = new CompraProduto();
                compraProduto.setCompra_id(resultSet.getInt("compra_id"));
                compraProduto.setProduto_id(resultSet.getInt("produto_id"));
                compraProduto.setQuantidade(resultSet.getInt("qtd"));
                resultado.add(compraProduto);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<CompraProduto>();
        }
        return resultado;
    }

    /**
     * Método utilizado para listar uma compraProdutos pelo id da compra
     *
     * @param compra_id
     * @return
     */
    public List<CompraProduto> listarCompraProdutoPorCompra_id(int compra_id) {
        List<CompraProduto> resultado = new ArrayList<CompraProduto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT compra_id, produto_id, qtd FROM compras_produtos WHERE compra_id = ?");
            preparedStatement.setInt(1, compra_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CompraProduto compraProduto = new CompraProduto();
                compraProduto.setCompra_id(resultSet.getInt("compra_id"));
                compraProduto.setProduto_id(resultSet.getInt("produto_id"));
                compraProduto.setQuantidade(resultSet.getInt("qtd"));
                resultado.add(compraProduto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<CompraProduto>();
        }
        return resultado;
    }
    
    /**
     * Método utilizado para listar uma compraProdutos pelo id do produto
     *
     * @param produto_id
     * @return
     */
    public CompraProduto listarCompraProdutoPorProduto_id(int produto_id) {
        CompraProduto compraProduto = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT compra_id, produto_id, qtd FROM compras_produtos WHERE produto_id = ?");
            preparedStatement.setInt(1, produto_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                compraProduto = new CompraProduto();
                compraProduto.setCompra_id(resultSet.getInt("compra_id"));
                compraProduto.setProduto_id(resultSet.getInt("produto_id"));
                compraProduto.setQuantidade(resultSet.getInt("qtd"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return compraProduto;
    }
    
    public List<ValorRecebidoPorDia> listarValorRecebidoPorDia(){
        List<ValorRecebidoPorDia> resultado = new ArrayList<ValorRecebidoPorDia>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("select SUM(p.preco * cp.qtd) AS total, cast(c.data_compra AS date) AS stat_day from produtos p\n" +
                                                                        "join compras_produtos cp\n" +
                                                                        "on cp.produto_id = p.id\n" +
                                                                        "join compras c\n" +
                                                                        "on cp.compra_id = c.id\n" +
                                                                        "GROUP BY cast(c.data_compra as date)\n" +
                                                                        "order by stat_day;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ValorRecebidoPorDia vrpd = new ValorRecebidoPorDia();
                vrpd.setDia(resultSet.getDate("stat_day"));
                vrpd.setValor(resultSet.getDouble("total"));
                resultado.add(vrpd);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<ValorRecebidoPorDia>();
        }
        return resultado;
    }

    /**
     * Método utilizado para inserir uma nova compraProduto
     *
     * @param compra_id
     * @param produto_id
     * @param qtd
     * @return
     */
    public boolean inserir(int compra_id, int produto_id, int qtd) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO compras_produtos (compra_id, produto_id, qtd) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, compra_id);
            preparedStatement.setInt(2, produto_id);
            preparedStatement.setInt(3, qtd);
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
     * Método utilizado para alterar uma compraProduto já existente
     *
     * @param compra_id
     * @param produto_id
     * @param data
     * @return
     */
    public boolean alterar(int compra_id, int produto_id, int qtd) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE compras_produtos SET qtd = ? WHERE compra_id = ? AND produto_id = ?");
            preparedStatement.setInt(1, qtd);
            preparedStatement.setInt(2, compra_id);
            preparedStatement.setInt(3, produto_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
    /**
     * Método para deletar uma compraProduto já existente, utilizando as 2 ids
     *
     * @param compra_id
     * @param produto_id
     * @return
     */
    public boolean deletar(int compra_id, int produto_id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM compras_produtos WHERE compra_id = ? AND produto_id = ?");
            preparedStatement.setInt(1, compra_id);
            preparedStatement.setInt(2, produto_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
}