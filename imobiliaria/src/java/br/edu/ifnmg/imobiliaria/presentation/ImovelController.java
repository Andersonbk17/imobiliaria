/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Foto;
import br.edu.ifnmg.imobiliaria.domainModel.IImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Anderson
 */
@Named(value = "imovelController")
@SessionScoped
public class ImovelController implements Serializable{

    /**
     * Creates a new instance of ImovelController
     */
    @EJB
    IImovelRepositorio dao;

    Imovel entidade;
    Imovel filtro;
    List<Imovel> listagem;
    Foto foto;

    public ImovelController() {
        this.filtro = new Imovel();
        listagem = null;
        entidade = new Imovel();
        foto = new Foto();
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
        if(dao.Salvar(entidade)){
            listagem = null;
            entidade = new Imovel();
            exibirMensagem("Salvo com Sucesso !");
        }else{
            exibirMensagem("Erro ao salvar!");
        }
        
        
    }
    
     public List<Imovel> autoCompletar(String tmp){
        Imovel c = new Imovel();
        c.setId(Long.parseLong(tmp));
        return dao.Buscar(c);
    }

    public String editar() {
        return "CadastroImovel.xhtml";
    }

    public String criar() {
        entidade = new Imovel();
        return "CadastroImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemImovel.xhtml";
    }

    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemImovel.xhtml";
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {  
       // FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
        exibirMensagem(event.getFile().getFileName() );
    }  
    
    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        Connection conn;
        String arquivo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/ImoveisMaisProcurados.jasper");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Imobiliaria", "root", "");
            java.sql.Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("SELECT\n"
                    + "     COUNT(imovel.`ID`) AS Quantidade,\n"
                    + "     imovel.`ENDERECOBAIRRO` AS BAIRRO,\n"
                    + "     imovel.`ENDERECOCEP` AS CEP,\n"
                    + "     imovel.`ENDERECOCOMPLEMENTO` AS COMPLEMENTO,\n"
                    + "     imovel.`ENDERECONUMERO` AS NUMERO,\n"
                    + "     imovel.`ENDERECORUA` AS RUA,\n"
                    + "     pessoa.`EMAIL` AS EMAIL,\n"
                    + "     pessoa.`NOME` AS Proprietario,\n"
                    + "     pessoa.`TELEFONE` AS TELEFONE,\n"
                    + "     cidade.`NOME` AS Cidade\n"
                    + "FROM\n"
                    + "     `pessoa` pessoa INNER JOIN `cliente` cliente ON pessoa.`ID` = cliente.`ID`\n"
                    + "     INNER JOIN `imovel` imovel ON cliente.`ID` = imovel.`CLIENTEPROPRIETARIO_ID`\n"
                    + "     INNER JOIN `interessado` interessado ON imovel.`ID` = interessado.`IMOVEL_ID`\n"
                    + "     INNER JOIN `cidade` cidade ON imovel.`CIDADE_ID` = cidade.`ID`\n"
                    + "GROUP BY\n"
                    + "     imovel_ID\n"
                    + "ORDER BY\n"
                    + "    Quantidade DESC");
            JRDataSource ds = new JRResultSetDataSource(rs);
            JasperPrint jasperPrint = JasperFillManager.fillReport(arquivo, null, ds);

         //    JasperViewer.viewReport(jasperPrint, false);  
            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");

                //String nome = usuario.getNome().replace(" ", "_");
                //Código abaixo gerar o relatório e disponibiliza diretamente na página 
            //   res.setHeader("Content-disposition", "inline;filename=" + nome + "_Alergias.pdf");
            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar 
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();

           // HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            //  httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
            // ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            // JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            // FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public void addFoto(){
        entidade.addFoto(foto);
        foto = new Foto();
    }
    public List<Imovel> listarTodos() {
        listagem = dao.Buscar(filtro);
        return listagem;
    }

    public String voltar() {
        listagem = null;
        return "ListagemImovel.xhtml";
    }

    public IImovelRepositorio getDao() {
        return dao;
    }

    public void setDao(IImovelRepositorio dao) {
        this.dao = dao;
    }

    public Imovel getEntidade() {
        return entidade;
    }

    public void setEntidade(Imovel entidade) {
        this.entidade = entidade;
    }

    public Imovel getFiltro() {
        return filtro;
    }

    public void setFiltro(Imovel filtro) {
        this.filtro = filtro;
    }

    public List<Imovel> getListagem() {
        return listagem;
    }

    public void setListagem(List<Imovel> listagem) {
        this.listagem = listagem;
    }

}
