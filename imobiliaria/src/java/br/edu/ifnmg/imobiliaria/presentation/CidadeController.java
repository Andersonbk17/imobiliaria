/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cidade;
import br.edu.ifnmg.imobiliaria.domainModel.ICidadeRepositorio;
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
@Named(value = "CidadeController")
@RequestScoped
public class CidadeController {

    /**
     * Creates a new instance of CidadeController
     */
    @EJB
    ICidadeRepositorio dao;
    Cidade entidade;
    Cidade filtro;
    List<Cidade> listagem;
    
    public CidadeController() {
        entidade = new Cidade();
        filtro = new Cidade();
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
        return "ListagemCidade.xhtml";
    }
    
    public String criar(){
        entidade = new Cidade();
        return "CadastroCidade.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCidade.xhtml";
    }
    
    public String voltar(){
        return "index.xhtml";
    }

    public ICidadeRepositorio getDao() {
        return dao;
    }

    public void setDao(ICidadeRepositorio dao) {
        this.dao = dao;
    }

    public Cidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Cidade entidade) {
        this.entidade = entidade;
    }

    public Cidade getFiltro() {
        return filtro;
    }

    public void setFiltro(Cidade filtro) {
        this.filtro = filtro;
    }

    public List<Cidade> getListagem() {
        return listagem;
    }

    public void setListagem(List<Cidade> listagem) {
        this.listagem = listagem;
    }
    
    
    
}
