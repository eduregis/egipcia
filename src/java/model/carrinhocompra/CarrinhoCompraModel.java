/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.carrinhocompra;

import java.util.ArrayList;
import java.util.List;
import model.produto.Produto;
import model.produto.ProdutoModel;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de negócio que presenta todas as regras de negócio da entidade
 * carrinho de compra
 */
public final class CarrinhoCompraModel {

    private static final String SEPARADOR_DE_ITENS = "SEP_ITENS";
    private static final String SEPARADOR_NO_ITEM = "SEP_REGISTRO";

    private CarrinhoCompraModel() {
    }

    /**
     * Método utilizado para obter o carrinho de compras de acordo com o valor
     * do cookie
     *
     * @param valor
     * @return
     */
    public static final List<CarrinhoCompraItem> obterCarrinhoCompra(String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = new ArrayList<CarrinhoCompraItem>();
        // se o valor do cookie está vazio ou não há separador no item, então está vazio
        if (valor == null || valor.trim().length() == 0 || !valor.contains(SEPARADOR_NO_ITEM)) {
            return carrinhoCompraItens;
        }
        // caso exista algo no carrinho de compras
        ProdutoModel produtoModel = new ProdutoModel();
        // se o valor do cookie contenha separador de itens, então tem mais de dois itens
        if (valor.contains(SEPARADOR_DE_ITENS)) {
            String[] itens = valor.split(SEPARADOR_DE_ITENS);
            for (int i = 0; itens != null && i < itens.length; i++) {
                String[] item = itens[i].split(SEPARADOR_NO_ITEM);
                CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
                Produto produto = produtoModel.listar(Integer.parseInt(item[0]));
                carrinhoCompraItem.setProduto(produto);
                carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));
                carrinhoCompraItens.add(carrinhoCompraItem);
            }
        // caso o valor tenha apenas um item
        } else {
            String[] item = valor.split(SEPARADOR_NO_ITEM);
            CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
            Produto produto = produtoModel.listar(Integer.parseInt(item[0]));
            carrinhoCompraItem.setProduto(produto);
            carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));
            carrinhoCompraItens.add(carrinhoCompraItem);
        }
        return carrinhoCompraItens;
    }

    /**
     * Método utilizado para adicionar um novo item de produto ao carrinho de compras
     * 
     * @param produtoId
     * @param quantidade
     * @param valor
     * @return 
     */
    public static final String adicionarItem(int produtoId, int quantidade, String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return produtoId + SEPARADOR_NO_ITEM + quantidade;
        }
        boolean adicionou = false;
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                carrinhoCompraItem.setQuantidade(carrinhoCompraItem.getQuantidade() + quantidade);
                adicionou = true;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_DE_ITENS;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_NO_ITEM + carrinhoCompraItem.getQuantidade();
        }
        if (!adicionou) {
            resultado += SEPARADOR_DE_ITENS + produtoId + SEPARADOR_NO_ITEM + quantidade;
        }
        return resultado;
    }

    /**
     * Método utilizado para remover um produto do carrinho de compras
     * 
     * @param produtoId
     * @param valor
     * @return 
     */
    public static final String removerItem(int produtoId, String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return "";
        }
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                continue;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_DE_ITENS;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_NO_ITEM + carrinhoCompraItem.getQuantidade();
        }
        return resultado;
    }

}
