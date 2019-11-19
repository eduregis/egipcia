/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.relatorios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author eduardo
 */
public class RelatorioDOCProdutoForaDeEstoqueServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        XWPFDocument documento = new XWPFDocument();
        
        XWPFParagraph titulo = documento.createParagraph();
        titulo.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun tituloConteudo = titulo.createRun();
        tituloConteudo.setText("Produtos Fora de Estoque");
        tituloConteudo.setColor("000000");
        tituloConteudo.setBold(true);
        tituloConteudo.setFontFamily("Times");
        tituloConteudo.setFontSize(20);

        XWPFTable tabelaProdutos = documento.createTable();

        XWPFTableRow tabelaLinhaTitulo = tabelaProdutos.getRow(0);
        tabelaLinhaTitulo.getCell(0).setText("Id");
        tabelaLinhaTitulo.addNewTableCell().setText("Descrição");
        tabelaLinhaTitulo.addNewTableCell().setText("Preço (R$)");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarProdutosForaDeEstoque();

        NumberFormat numberFormat = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));
        for (int i = 0; produtos != null && i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            XWPFTableRow tabelaLinhaConteudo = tabelaProdutos.createRow();
            tabelaLinhaConteudo.getCell(0).setText(String.valueOf(p.getId()));
            tabelaLinhaConteudo.getCell(1).setText(p.getNome());
            tabelaLinhaConteudo.getCell(2).setText(numberFormat.format(p.getPreco()));
        }
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        documento.write(byteArrayOutputStream);
        byte[] byteOutputArray = byteArrayOutputStream.toByteArray();

        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=produtosForaDeEstoque.doc");
        response.setContentLength(byteOutputArray.length);

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(byteOutputArray);
            outputStream.flush();
        }
    }
}
