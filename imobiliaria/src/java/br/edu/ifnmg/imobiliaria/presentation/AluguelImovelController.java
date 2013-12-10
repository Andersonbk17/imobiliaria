/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.AluguelImovel;
import br.edu.ifnmg.imobiliaria.domainModel.IAluguelImovelRepositorio;
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
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Anderson
 */
@Named(value = "aluguelImovelController")
@SessionScoped
public class AluguelImovelController implements Serializable{

    /**
     * Creates a new instance of AluguelImovelController
     */
    @EJB
    IAluguelImovelRepositorio dao;
    AluguelImovel entidade;
    AluguelImovel filtro;
    List<AluguelImovel> listagem;
    public AluguelImovelController() {
        entidade = new AluguelImovel();
        filtro = new AluguelImovel();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
        adicionaFuncionario();
        
        if(dao.Salvar(entidade)){
            listagem = null;
            exibirMensagem("Salvo com Sucesso!");
            entidade = new AluguelImovel();
        }else{
                exibirMensagem("Erro ao Salvar!");
        }
        

    }

    public void adicionaFuncionario(){
         AutenticacaoController a = new AutenticacaoController();
                 
         entidade.setFuncionario(a.pegarDaSessao().getFuncionario());
         
     
     }
    public String editar() {
        return "CadastroAluguelImovel.xhtml";
    }

    public String criar() {
        entidade = new AluguelImovel();
        return "CadastroAluguelImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemAluguelImovel.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "ListagemAluguelImovel.xhtml";
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemAluguelImovel.xhtml";
    }
     
     public List<AluguelImovel>listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }

    public IAluguelImovelRepositorio getDao() {
        return dao;
    }

    public void setDao(IAluguelImovelRepositorio dao) {
        this.dao = dao;
    }

    public AluguelImovel getEntidade() {
        return entidade;
    }

    public void setEntidade(AluguelImovel entidade) {
        this.entidade = entidade;
    }

    public AluguelImovel getFiltro() {
        return filtro;
    }

    public void setFiltro(AluguelImovel filtro) {
        this.filtro = filtro;
    }

    public List<AluguelImovel> getListagem() {
        return listagem;
    }

    public void setListagem(List<AluguelImovel> listagem) {
        this.listagem = listagem;
    }
     
    /*
    
    
                    RELATÓRIO
    
    */
    
    
    public void PDFImoveisMaisAlugados(ActionEvent actionEvent) throws JRException, IOException {
        Connection conn;
        String arquivo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/ImovesMaisAlugados.jasper");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Imobiliaria", "root", "");
            java.sql.Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("SELECT\n"
                    + "     count(aluguelimovel.`IMOVEL_ID`) AS qtd_Alugadas,\n"
                    + "     imovel.`ENDERECOCEP` AS CEP,\n"
                    + "     imovel.`ENDERECOCOMPLEMENTO` AS COMPLEMENTO,\n"
                    + "     imovel.`ENDERECONUMERO` AS NUMERO,\n"
                    + "     imovel.`ENDERECORUA` AS RUA,\n"
                    + "     imovel.`ENDERECOBAIRRO` AS BAIRRO,\n"
                    + "     cidade.`NOME` AS CIDADE,\n"
                    + "     imovel.`ID` AS ID_Imovel,\n"
                    + "     pessoa.`NOME` AS Proprietario,\n"
                    + "     pessoa.`TELEFONE` AS TELEFONE\n"
                    + "FROM\n"
                    + "     `imovel` imovel INNER JOIN `aluguelimovel` aluguelimovel ON imovel.`ID` = aluguelimovel.`IMOVEL_ID`\n"
                    + "     INNER JOIN `cidade` cidade ON imovel.`CIDADE_ID` = cidade.`ID`\n"
                    + "     INNER JOIN `pessoa` pessoa ON imovel.`CLIENTEPROPRIETARIO_ID` = pessoa.`ID`\n"
                    + "GROUP BY\n"
                    + "     ID_Imovel\n"
                    + "ORDER BY\n"
                    + "     qtd_Alugadas DESC");
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

}
