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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cliente.ClienteModel;
import model.compra.CompraDAO;
import model.compraProduto.CompraProdutoDAO;
import model.relatorios.ComprasPorCliente;
import model.relatorios.ValorRecebidoPorDia;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author eduardo
 */
public class RelatorioXLSValorRecebidoPorDiaServlet extends HttpServlet {

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
        HSSFSheet sheetProdutos = workbook.createSheet("Total de Vendas por Cliente");
        
        CompraProdutoDAO compraProdutoDAO = new CompraProdutoDAO();
        List<ValorRecebidoPorDia> vrpds = compraProdutoDAO.listarValorRecebidoPorDia();

        int numeroLinha = 0;

        for (int i = 0; vrpds != null && i < vrpds.size(); i++) {
            ValorRecebidoPorDia vrpd = vrpds.get(i);
            Row linha = null;
            if (i == 0) {
                linha = sheetProdutos.createRow(numeroLinha++);

                Cell cellId = linha.createCell(0);
                cellId.setCellValue("Data");

                Cell cellDescricao = linha.createCell(1);
                cellDescricao.setCellValue("Valor recebido");
            }

            linha = sheetProdutos.createRow(numeroLinha++);

            Cell cellId = linha.createCell(0);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            cellId.setCellValue(dateFormat.format(vrpd.getDia()));

            Cell cellDescricao = linha.createCell(1);
            cellDescricao.setCellValue(vrpd.getValor());
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        byte[] byteOutputArray = byteArrayOutputStream.toByteArray();

        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=comprasPorCliente.xls");
        response.setContentLength(byteOutputArray.length);

        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(byteOutputArray);
            outputStream.flush();
        }
    }
}
