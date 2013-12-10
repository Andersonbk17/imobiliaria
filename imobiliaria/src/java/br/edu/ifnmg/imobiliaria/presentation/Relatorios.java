/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "relatorios")
@SessionScoped
public class Relatorios implements Serializable {

    /**
     * Creates a new instance of Relatorios
     */
    public Relatorios() {
    }
    
    public void relatorioFolhaDePagemento(){
    
    }
    
    
    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        Connection conn;
        String arquivo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/TotalGastoFuncionario.jasper");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Imobiliaria", "root", "");
            java.sql.Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("SELECT\n"
                    + "     \n"
                    + "     cargo.`NOME` AS CARGO,\n"
                    + "     cargo.`SALARIO` AS SALARIO,\n"
                    + "     pessoa.`CPF` AS CPF,\n"
                    + "     pessoa.`NOME` AS FUNCIONARIO,\n"
                    + "     pessoa.`TELEFONE` AS TELEFONE,\n"
                    + "     pessoa.`EMAIL` AS EMAIL,\n"
                    + "     funcionario.`DATAADMISSAO` AS DATA_ADMISSAO,\n"
                    + "     funcionario.`ID` AS ID_FUNCIONARIO\n"
                    + "FROM\n"
                    + "     `cargo` cargo INNER JOIN `funcionario` funcionario ON cargo.`ID` = funcionario.`CARGO_ID`\n"
                    + "     INNER JOIN `pessoa` pessoa ON funcionario.`ID` = pessoa.`ID`\n"
                    + "ORDER BY\n"
                    + "     pessoa.`NOME` ASC");
            JRDataSource ds = new JRResultSetDataSource(rs);
            JasperPrint jasperPrint = JasperFillManager.fillReport(arquivo, null, ds);

            //    JasperViewer.viewReport(jasperPrint, false);  
            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");

             
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();

            
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ImovelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        Connection conn;
        String arquivo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/TotalGastoFuncionario.jasper");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Imobiliaria", "root", "");
            java.sql.Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("SELECT\n"
                    + "     \n"
                    + "     cargo.`NOME` AS CARGO,\n"
                    + "     cargo.`SALARIO` AS SALARIO,\n"
                    + "     pessoa.`CPF` AS CPF,\n"
                    + "     pessoa.`NOME` AS FUNCIONARIO,\n"
                    + "     pessoa.`TELEFONE` AS TELEFONE,\n"
                    + "     pessoa.`EMAIL` AS EMAIL,\n"
                    + "     funcionario.`DATAADMISSAO` AS DATA_ADMISSAO,\n"
                    + "     funcionario.`ID` AS ID_FUNCIONARIO\n"
                    + "FROM\n"
                    + "     `cargo` cargo INNER JOIN `funcionario` funcionario ON cargo.`ID` = funcionario.`CARGO_ID`\n"
                    + "     INNER JOIN `pessoa` pessoa ON funcionario.`ID` = pessoa.`ID`\n"
                    + "ORDER BY\n"
                    + "     pessoa.`NOME` ASC");
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
    */
    
}
