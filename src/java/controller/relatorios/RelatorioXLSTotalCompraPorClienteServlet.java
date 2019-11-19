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
import model.cliente.ClienteModel;
import model.compra.CompraDAO;
import model.produto.Produto;
import model.produto.ProdutoDAO;
import model.relatorios.ComprasPorCliente;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author eduardo
 */
public class RelatorioXLSTotalCompraPorClienteServlet extends HttpServlet {

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
        
        CompraDAO compraDAO = new CompraDAO();
        ClienteModel clienteModel = new ClienteModel();
        List<ComprasPorCliente> cpcs = compraDAO.listarComprasPorCliente();

        int numeroLinha = 0;

        for (int i = 0; cpcs != null && i < cpcs.size(); i++) {
            ComprasPorCliente cpc = cpcs.get(i);
            Row linha = null;
            if (i == 0) {
                linha = sheetProdutos.createRow(numeroLinha++);

                Cell cellId = linha.createCell(0);
                cellId.setCellValue("Id");

                Cell cellDescricao = linha.createCell(1);
                cellDescricao.setCellValue("Nome");

                Cell cellPreco = linha.createCell(2);
                cellPreco.setCellValue("Numero de compras");
            }

            linha = sheetProdutos.createRow(numeroLinha++);

            Cell cellId = linha.createCell(0);
            cellId.setCellValue(cpc.getCliente_id());

            Cell cellDescricao = linha.createCell(1);
            cellDescricao.setCellValue(clienteModel.listar(cpc.getCliente_id()).getNome());

            Cell cellPreco = linha.createCell(2);
            cellPreco.setCellValue(cpc.getQtd());
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
