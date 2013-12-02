/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.FormaDePagamento;
import br.edu.ifnmg.imobiliaria.domainModel.IFormaDePagamentoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anderson
 */
@Named(value = "formaDePagamentoController")
@SessionScoped
public class FormaDePagamentoController implements Serializable{

    /**
     * Creates a new instance of FormaDePagamentoController
     */
    @EJB
    IFormaDePagamentoRepositorio dao;
    FormaDePagamento entidade;
    FormaDePagamento filtro;
    List<FormaDePagamento> listagem;
    
    
    public FormaDePagamentoController() {
        this.entidade = new FormaDePagamento();
        this.filtro = new FormaDePagamento();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        try{
            if(dao.verificaESalva(entidade)){
            exibirMensagem("Salvo com Sucesso!");
            listagem = null;
            entidade = new FormaDePagamento();
        }else{
            exibirMensagem("Erro ao salvar!");
        }
        
        }catch(Exception ex){
            exibirMensagem(ex.getMessage());
        }
        
        
        

    }

    public String editar() {
        return "CadastroFormaDePagamento.xhtml";
    }

    public String criar() {
        entidade = new FormaDePagamento();
        return "CadastroFormaDePagamento.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemFormaDePagamento.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "ListagemFormaDePagamento.xhtml";
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemFormaDePagamento.xhtml";
    }
     
     public List<FormaDePagamento>listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }

    public IFormaDePagamentoRepositorio getDao() {
        return dao;
    }

    public void setDao(IFormaDePagamentoRepositorio dao) {
        this.dao = dao;
    }

    public FormaDePagamento getEntidade() {
        return entidade;
    }

    public void setEntidade(FormaDePagamento entidade) {
        this.entidade = entidade;
    }

    public FormaDePagamento getFiltro() {
        return filtro;
    }

    public void setFiltro(FormaDePagamento filtro) {
        this.filtro = filtro;
    }

    public List<FormaDePagamento> getListagem() {
        return listagem;
    }

    public void setListagem(List<FormaDePagamento> listagem) {
        this.listagem = listagem;
    }
     
     
    
}
