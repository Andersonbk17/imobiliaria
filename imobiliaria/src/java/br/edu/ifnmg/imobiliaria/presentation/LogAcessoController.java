/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.ILogAcessoRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.LogAcesso;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author emerson
 */
@Named(value = "logAcessoController")
@SessionScoped
public class LogAcessoController implements Serializable{

    /**
     * Creates a new instance of LogAcessoController
     */
    @EJB
    ILogAcessoRepositorio dao;
    LogAcesso entidade;
    LogAcesso filtro;
    List<LogAcesso> listagem;
    
    
    public LogAcessoController() {
        entidade = new LogAcesso();
        filtro = new LogAcesso();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }
    
    public void salvar(){
        dao.Salvar(entidade);
        
       
    }
    
    public String editar(){
        return "";
    }
    
    public String criar(){
        entidade = new LogAcesso();
        return "CadastroLogAcesso.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemLogAcesso.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemLogAcesso.xhtml";
    }

    
    public String voltar(){
        listagem = null;
        return "ListagemLogAcesso.xhtml";
    }

    public ILogAcessoRepositorio getDao() {
        return dao;
    }

    public void setDao(ILogAcessoRepositorio dao) {
        this.dao = dao;
    }

    public LogAcesso getEntidade() {
        return entidade;
    }

    public void setEntidade(LogAcesso entidade) {
        this.entidade = entidade;
    }

    public LogAcesso getFiltro() {
        return filtro;
    }

    public void setFiltro(LogAcesso filtro) {
        this.filtro = filtro;
    }

    public List<LogAcesso> getListagem() {
        return listagem;
    }

    public void setListagem(List<LogAcesso> listagem) {
        this.listagem = listagem;
    }
    
    
    
    
}
