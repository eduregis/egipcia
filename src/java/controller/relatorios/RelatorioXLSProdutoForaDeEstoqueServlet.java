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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author eduardo
 */
public class RelatorioXLSProdutoForaDeEstoqueServlet extends HttpServlet {

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
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetProdutos = workbook.createSheet("Produtos");

        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarProdutosForaDeEstoque();

        int numeroLinha = 0;

        for (int i = 0; produtos != null && i < produtos.size(); i++) {
            Row linha = null;
            if (i == 0) {
                linha = sheetProdutos.createRow(numeroLinha++);

                Cell cellId = linha.createCell(0);
                cellId.setCellValue("Id");

                Cell cellDescricao = linha.createCell(1);
                cellDescricao.setCellValue("Descrição");

                Cell cellPreco = linha.createCell(2);
                cellPreco.setCellValue("Preço");
            }

            Produto p = produtos.get(i);

            linha = sheetProdutos.createRow(numeroLinha++);

            Cell cellId = linha.createCell(0);
            cellId.setCellValue(p.getId());

            Cell cellDescricao = linha.createCell(1);
            cellDescricao.setCellValue(p.getNome());

            Cell cellPreco = linha.createCell(2);
            cellPreco.setCellValue(p.getPreco());
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        byte[] byteOutputArray = byteArrayOutputStream.toByteArray();

        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=produtosForaDeEstoque.xls");
        response.setContentLength(byteOutputArray.length);

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(byteOutputArray);
            outputStream.flush();
        }
    }
}
