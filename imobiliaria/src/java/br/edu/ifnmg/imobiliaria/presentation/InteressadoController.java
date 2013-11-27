/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IInteressadoRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Interessado;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author emerson
 */
@Named(value = "interessadoController")
@RequestScoped
public class InteressadoController {

    /**
     * Creates a new instance of InteressadoController
     */
    @EJB
    IInteressadoRepositorio dao;
    Interessado entidade;
    Interessado filtro;
    List<Interessado> listagem;
    
    public InteressadoController() {
        entidade = new Interessado();
        filtro = new Interessado();  
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    public void salvar(){
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com sucesso!");
    }
    
    public String editar(){
        return "ListagemInteressado.xhtml";
    }
    
    public String criar(){
        entidade = new Interessado();
        return "CadastradoCargo.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCargo.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemInteressado.xhtml";
    }
    
    public String voltar(){
        listagem = null;
        return "index.xhtml";
    }

    public IInteressadoRepositorio getDao() {
        return dao;
    }

    public void setDao(IInteressadoRepositorio dao) {
        this.dao = dao;
    }

    public Interessado getEntidade() {
        return entidade;
    }

    public void setEntidade(Interessado entidade) {
        this.entidade = entidade;
    }

    public Interessado getFiltro() {
        return filtro;
    }

    public void setFiltro(Interessado filtro) {
        this.filtro = filtro;
    }

    public List<Interessado> getListagem() {
        return listagem;
    }

    public void setListagem(List<Interessado> listagem) {
        this.listagem = listagem;
    }
    
    
    
}
