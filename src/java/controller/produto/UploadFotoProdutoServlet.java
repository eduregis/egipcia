/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.produto;

import static config.Configuracao.REPOSITORIO_IMAGEM_PRODUTOS;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.produto.ProdutoModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Leonardo Oliveira Moreira
 *
 * Classe de controle que realiza a ação de upload de uma foto para um produto
 */
public class UploadFotoProdutoServlet extends HttpServlet {

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
        System.out.println("Entrou");
        int id = -1;
        FileItem foto = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        /* se a requisição suporta multipart/form-data */
        if (isMultipart) {
            boolean sucessoUpload = false;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                /* cria um diretório temporário para receber os dados/fragmentos do upload */
                factory.setRepository(new File(REPOSITORIO_IMAGEM_PRODUTOS + File.separator + "temp"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                /* recupera os parâmetros da requisição */
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                Iterator<FileItem> iter = items.iterator();

                /* percorre os parâmetros da requisição */
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    /* caso o parâmetro seja do tipo texto e com a chave id */
                    if (item.isFormField() && item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    }
                    /* caso o parâmetro seja do tipo binário e com chave foto */
                    if (!item.isFormField() && item.getFieldName().equals("foto") && item.getContentType().startsWith("image/")) {
                        foto = item;
                    }
                }

                ProdutoModel produtoModel = new ProdutoModel();

                /* se tem dados de id e foto válidos, gravar a foto no sistema de arquivos e 
                alterar o registro do produto com o caminho da foto */
                System.out.println(id);
                System.out.println(foto);
                if (id != -1 && foto != null) {
                    foto.write(new File(REPOSITORIO_IMAGEM_PRODUTOS + File.separator + id + foto.getName().substring(foto.getName().lastIndexOf("."))));
                    System.out.println(id + foto.getName().substring(foto.getName().lastIndexOf(".")));
                    produtoModel.alterarFoto(id, id + foto.getName().substring(foto.getName().lastIndexOf(".")));
                    sucessoUpload = true;
                }

                /* caso não tenha sucesso na gravação da foto no sistema de arquivos ou 
                não foi possível alterar o registro do produto */
                if (!sucessoUpload) {
                    request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
                } else {
                    request.setAttribute("mensagem", "Upload da foto deste produto foi efetuado com sucesso");
                }
            } catch (Exception ex) {
                request.setAttribute("mensagem", "Não foi possível processar o upload da foto deste produto");
            }
            RequestDispatcher rd = request.getRequestDispatcher("MostrarProduto?id=" + id);
            rd.forward(request, response);
        }
    }

}
