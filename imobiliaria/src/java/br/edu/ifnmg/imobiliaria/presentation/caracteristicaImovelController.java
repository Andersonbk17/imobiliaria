/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Caracteristica;
import br.edu.ifnmg.imobiliaria.domainModel.ICaracteristicaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anderson
 */
@Named(value = "caracteristicaImovelController")
@RequestScoped
public class caracteristicaImovelController implements Serializable {

    /**
     * Creates a new instance of caracteristicaImovelController
     */
    @EJB
    ICaracteristicaRepositorio dao;
    Caracteristica entidade;
    Caracteristica filtro;
    List<Caracteristica> listagem;
    
    
    public caracteristicaImovelController() {
        entidade = new Caracteristica();
        filtro = new Caracteristica();
    }
    
     public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com Sucesso!");

    }

    public String editar() {
        return "CadastroTipoDeImovel.xhtml";
    }

    public String criar() {
        entidade = new Caracteristica();
        return "CadastroTipoDeImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "TipoImovelListagem.xhtml";
    }
    
    public String voltar() {
        listagem = null;
        return "index.xhtml";
    }

    public ICaracteristicaRepositorio getDao() {
        return dao;
    }

    public void setDao(ICaracteristicaRepositorio dao) {
        this.dao = dao;
    }

    public Caracteristica getEntidade() {
        return entidade;
    }

    public void setEntidade(Caracteristica entidade) {
        this.entidade = entidade;
    }

    public Caracteristica getFiltro() {
        return filtro;
    }

    public void setFiltro(Caracteristica filtro) {
        this.filtro = filtro;
    }

    public List<Caracteristica> getListagem() {
        return listagem;
    }

    public void setListagem(List<Caracteristica> listagem) {
        this.listagem = listagem;
    }
    
    
    
}
