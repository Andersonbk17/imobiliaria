/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.INacionalidadeRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Nacionalidade;
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
@Named(value = "NacionalidadeController")
@SessionScoped
public class NacionalidadeController implements Serializable{

    /**
     * Creates a new instance of NacionalidadeController
     */
    @EJB
    INacionalidadeRepositorio dao;
    Nacionalidade entidade;
    Nacionalidade filtro;
    List<Nacionalidade> listagem;
    
    public NacionalidadeController() {
        entidade = new Nacionalidade();
        filtro = new Nacionalidade();
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
        return "CadastroNacionalidade.xhtml";
    }

    public String criar() {
        entidade = new Nacionalidade();
        return "CadastroNacionalidade.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemNacionalidade.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "index.xhtml";
    }
    
    public List<Nacionalidade> listarNacionalidade(){
        listagem = dao.Buscar(filtro);
        return listagem;
    }

    public INacionalidadeRepositorio getDao() {
        return dao;
    }

    public void setDao(INacionalidadeRepositorio dao) {
        this.dao = dao;
    }

    public Nacionalidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Nacionalidade entidade) {
        this.entidade = entidade;
    }

    public Nacionalidade getFiltro() {
        return filtro;
    }

    public void setFiltro(Nacionalidade filtro) {
        this.filtro = filtro;
    }

    public List<Nacionalidade> getListagem() {
        return listagem;
    }

    public void setListagem(List<Nacionalidade> listagem) {
        this.listagem = listagem;
    }
    
    
    
}
