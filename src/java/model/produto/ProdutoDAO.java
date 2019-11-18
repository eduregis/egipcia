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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.categoria.Categoria;

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
            ResultSet resultSet = statement.executeQuery("SELECT id, nome, descricao, qtd, preco, imagem FROM produtos");
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setQuantidade(resultSet.getInt("qtd"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("imagem"));
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

    public List<Produto> listarProdutosEstoque() {
        List<Produto> resultado = new ArrayList<Produto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome, descricao, qtd, preco, imagem FROM produtos WHERE qtd > 0");
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setQuantidade(resultSet.getInt("qtd"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("imagem"));
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
    
    public List<Produto> listarProdutosForaDeEstoque() {
        List<Produto> resultado = new ArrayList<Produto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome, descricao, qtd, preco, imagem FROM produtos WHERE qtd = 0");
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setQuantidade(resultSet.getInt("qtd"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setFoto(resultSet.getString("imagem"));
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
            try (
                    Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, imagem, descricao, preco, qtd FROM produtos WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        produto = new Produto();
                        produto.setId(resultSet.getInt("id"));
                        produto.setNome(resultSet.getString("nome"));
                        produto.setDescricao(resultSet.getString("descricao"));
                        produto.setFoto(resultSet.getString("imagem"));
                        produto.setPreco(resultSet.getDouble("preco"));
                        produto.setQuantidade(resultSet.getInt("qtd"));

                        PreparedStatement preparedStatementProdutoCategorias = connection.prepareStatement("SELECT c.id, c.descricao FROM produtos_categorias AS pc, categorias AS c WHERE pc.produto_id = ? AND c.id = pc.categoria_id");
                        preparedStatementProdutoCategorias.setInt(1, produto.getId());
                        ResultSet resultSetProdutoCategorias = preparedStatementProdutoCategorias.executeQuery();
                        List<Categoria> categorias = new ArrayList<>();
                        while (resultSetProdutoCategorias.next()) {
                            Categoria categoria = new Categoria();
                            categoria.setId(resultSetProdutoCategorias.getInt("id"));
                            categoria.setDescricao(resultSetProdutoCategorias.getString("descricao"));
                            categorias.add(categoria);
                        }
                        resultSetProdutoCategorias.close();
                        preparedStatementProdutoCategorias.close();

                        produto.setCategorias(categorias);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
        return produto;
    }

    /**
     * Método utilizado para inserir uma nova produto
     *
     * @param nome
     * @param descricao
     * @param qtd
     * @param preco
     * @param imagem
     * @return
     */
    public boolean inserir(String nome, String descricao, int qtd, double preco, List<Categoria> categorias) {
            boolean resultado = false;
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);

            int id = -1;
            Statement statementNovoId = connection.createStatement();
            ResultSet resultSetNovoId = statementNovoId.executeQuery("SELECT nextval('produtos_id_seq') AS id");
            while (resultSetNovoId.next()) {
                id = resultSetNovoId.getInt("id");
            }
            resultSetNovoId.close();
            statementNovoId.close();

            PreparedStatement preparedStatementInserirProduto = connection.prepareStatement("INSERT INTO produtos (id, nome, descricao, preco, qtd) VALUES (?, ?, ?, ?, ?)");
            preparedStatementInserirProduto.setInt(1, id);
            preparedStatementInserirProduto.setString(2, nome);
            preparedStatementInserirProduto.setString(3, descricao);
            preparedStatementInserirProduto.setDouble(4, preco);
            preparedStatementInserirProduto.setInt(5, qtd);
            if (preparedStatementInserirProduto.executeUpdate() <= 0) {
                throw new SQLException();
            }
            preparedStatementInserirProduto.close();

            for (int i = 0; categorias != null && i < categorias.size(); i++) {
                Categoria categoria = categorias.get(i);
                PreparedStatement preparedStatementInserirCategoria = connection.prepareStatement("INSERT INTO produtos_categorias (produto_id, categoria_id) VALUES (?, ?)");
                preparedStatementInserirCategoria.setInt(1, id);
                preparedStatementInserirCategoria.setInt(2, categoria.getId());
                if (preparedStatementInserirCategoria.executeUpdate() <= 0) {
                    throw new SQLException();
                }
                preparedStatementInserirCategoria.close();
            }

            connection.commit();
            connection.close();
            resultado = true;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex1) {

            }
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um produto já existente
     *
     * @param id
     * @param nome
     * @param descricao
     * @param qtd
     * @param preco
     * @param imagem
     * @return
     */
    public boolean alterar(int id, String nome, String descricao, int qtd, double preco, List<Categoria> categorias) {
        boolean resultado = false;
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);

            PreparedStatement preparedStatementAtualizarProduto = connection.prepareStatement("UPDATE produtos SET nome = ?, descricao = ?, preco = ?, qtd = ? WHERE id = ?");
            preparedStatementAtualizarProduto.setString(1, nome);
            preparedStatementAtualizarProduto.setString(2, descricao);
            preparedStatementAtualizarProduto.setDouble(3, preco);
            preparedStatementAtualizarProduto.setInt(4, qtd);
            preparedStatementAtualizarProduto.setInt(5, id);
            if (preparedStatementAtualizarProduto.executeUpdate() <= 0) {
                throw new SQLException();
            }
            preparedStatementAtualizarProduto.close();

            PreparedStatement preparedStatementDeletarCategorias = connection.prepareStatement("DELETE FROM produtos_categorias WHERE produto_id = ?");
            preparedStatementDeletarCategorias.setInt(1, id);
            preparedStatementDeletarCategorias.executeUpdate();
            preparedStatementDeletarCategorias.close();

            for (int i = 0; categorias != null && i < categorias.size(); i++) {
                Categoria categoria = categorias.get(i);
                PreparedStatement preparedStatementInserirCategoria = connection.prepareStatement("INSERT INTO produtos_categorias (produto_id, categoria_id) VALUES (?, ?)");
                preparedStatementInserirCategoria.setInt(1, id);
                preparedStatementInserirCategoria.setInt(2, categoria.getId());
                if (preparedStatementInserirCategoria.executeUpdate() <= 0) {
                    throw new SQLException();
                }
                preparedStatementInserirCategoria.close();
            }

            connection.commit();
            connection.close();
            resultado = true;
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex1) {

            }
            return false;
        }
        return resultado;
    }
    
    public boolean alterarFoto(int id, String foto) {
        System.out.println(foto);
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET imagem = ? WHERE id = ?");
            preparedStatement.setString(1, foto);
            preparedStatement.setInt(2, id);
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