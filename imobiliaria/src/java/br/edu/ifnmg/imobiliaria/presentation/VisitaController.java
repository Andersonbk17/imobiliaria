/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IVisitaRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Visita;
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
@Named(value = "visitaController")
@SessionScoped
public class VisitaController implements Serializable{

    /**
     * Creates a new instance of VisitaController
     */
    @EJB
    IVisitaRepositorio dao;
    Visita entidade;
    Visita filtro;
    List<Visita> listagem;
    
    public VisitaController() {
        this.entidade = new Visita();
        this.filtro = new Visita();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com Sucesso!");

    }

    public String editar() {
        return "CadastroVisita.xhtml";
    }

    public String criar() {
        entidade = new Visita();
        return "CadastroVisita.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "VisitaListagem.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "ListagemVisita.xhtml";
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemVisita.xhtml";
    }
     
     public List<Visita> listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }

    public IVisitaRepositorio getDao() {
        return dao;
    }

    public void setDao(IVisitaRepositorio dao) {
        this.dao = dao;
    }

    public Visita getEntidade() {
        return entidade;
    }

    public void setEntidade(Visita entidade) {
        this.entidade = entidade;
    }

    public Visita getFiltro() {
        return filtro;
    }

    public void setFiltro(Visita filtro) {
        this.filtro = filtro;
    }

    public List<Visita> getListagem() {
        return listagem;
    }

    public void setListagem(List<Visita> listagem) {
        this.listagem = listagem;
    }
     
     
    
}
