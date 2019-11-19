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
import model.cliente.ClienteModel;
import model.compra.CompraDAO;
import model.compraProduto.CompraProdutoDAO;
import model.relatorios.ComprasPorCliente;
import model.relatorios.ValorRecebidoPorDia;
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
public class RelatorioDOCValorRecebidoPorDiaServlet extends HttpServlet {

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
        tituloConteudo.setText("Total de Compras por Cliente");
        tituloConteudo.setColor("000000");
        tituloConteudo.setBold(true);
        tituloConteudo.setFontFamily("Times");
        tituloConteudo.setFontSize(20);

        XWPFTable tabelaProdutos = documento.createTable();

        XWPFTableRow tabelaLinhaTitulo = tabelaProdutos.getRow(0);
        tabelaLinhaTitulo.getCell(0).setText("Data");
        tabelaLinhaTitulo.addNewTableCell().setText("Valor Recebido");

        CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
        List<ValorRecebidoPorDia> vrpds = compraProdutoDAO.listarValorRecebidoPorDia();

        NumberFormat numberFormat = new DecimalFormat ("#,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));
        for (int i = 0; vrpds != null && i < vrpds.size(); i++) {
            ValorRecebidoPorDia vrpd = vrpds.get(i);
            XWPFTableRow tabelaLinhaConteudo = tabelaProdutos.createRow();
            tabelaLinhaConteudo.getCell(0).setText(String.valueOf(vrpd.getDia()));
            tabelaLinhaConteudo.getCell(1).setText(String.valueOf(vrpd.getValor()));
            
        }
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        documento.write(byteArrayOutputStream);
        byte[] byteOutputArray = byteArrayOutputStream.toByteArray();

        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=valorRecebidoPorDia.doc");
        response.setContentLength(byteOutputArray.length);

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(byteOutputArray);
            outputStream.flush();
        }
    }
}
