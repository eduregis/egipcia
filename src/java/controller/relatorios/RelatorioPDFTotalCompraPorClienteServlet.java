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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author eduardo
 */
public class RelatorioPDFTotalCompraPorClienteServlet extends HttpServlet {

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
        PDDocument documento = new PDDocument();

        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarProdutos();
        produtos.addAll(produtoDAO.listarProdutos());

        PDPage pagina = new PDPage(PDRectangle.A4);
        PDPageContentStream conteudoPagina = new PDPageContentStream(documento, pagina);
        conteudoPagina.beginText();
        conteudoPagina.newLineAtOffset(10, 820);
        conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 12f);
        conteudoPagina.setLeading(14.5f);
        conteudoPagina.showText("Produtos Cadastrados");
        conteudoPagina.newLine();
        conteudoPagina.setFont(PDType1Font.COURIER, 8f);
        conteudoPagina.setLeading(8.5f);

        int linha = 1;
        NumberFormat numberFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        for (int i = 0; produtos != null && i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            String id = String.valueOf(p.getId());
            String descricao = p.getDescricao();
            String preco = numberFormat.format(p.getPreco());
            while ((id + " " + descricao + " " + preco).length() > 120) {
                descricao = descricao.substring(0, descricao.length() - 1);
            }
            while ((id + " " + descricao + " " + preco).length() < 120) {
                descricao += " ";
            }

            conteudoPagina.showText((id + " " + descricao + " " + preco));
            conteudoPagina.newLine();
            linha++;
            if (linha == 95) {
                linha = 1;
                conteudoPagina.endText();
                conteudoPagina.close();
                documento.addPage(pagina);
                if (i == produtos.size() - 1) {
                    break;
                }
                pagina = new PDPage(PDRectangle.A4);
                conteudoPagina = new PDPageContentStream(documento, pagina);
                conteudoPagina.beginText();
                conteudoPagina.newLineAtOffset(10, 820);
                conteudoPagina.setFont(PDType1Font.COURIER, 8f);
                conteudoPagina.setLeading(8.5f);
            }
            if (i == produtos.size() - 1) {
                conteudoPagina.endText();
                conteudoPagina.close();
                documento.addPage(pagina);
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        documento.save(byteArrayOutputStream);
        documento.close();
        byte[] byteOutputArray = byteArrayOutputStream.toByteArray();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=produtos.pdf");
        response.setContentLength(byteOutputArray.length);

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(byteOutputArray);
            outputStream.flush();
        }
    }
}
