/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IReformaRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import br.edu.ifnmg.imobiliaria.domainModel.Reforma;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anderson
 */
@Named(value = "reformaController")
@RequestScoped
public class ReformaController {

    /**
     * Creates a new instance of ReformaController
     */
    
    @EJB
    IReformaRepositorio dao;
    Imovel entidadeImovel;
    Reforma entidade;
    
    public ReformaController() {
        entidade = new Reforma();
        entidadeImovel = new Imovel();
    }
    
    
      public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        if(dao.Salvar(entidade)){
            exibirMensagem("Salvo com Sucesso!");
        }else{
            exibirMensagem("Erro ao salvar !");
        }
    
    }

    public String editar() {
        return "CadastroTipoDeImovel.xhtml";
    }

    public String criar() {
        entidade = new Reforma();
        return "CadastroTipoDeImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
      //  listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "TipoImovelListagem.xhtml";
    }

    public String voltar() {
      //  listagem = null;
        return "index.xhtml";
    }
    
     public String filtrar() {
     //   listagem = dao.Buscar(filtro);
        return "ListagemTipoDeImovel.xhtml";
    }

    public IReformaRepositorio getDao() {
        return dao;
    }

    public void setDao(IReformaRepositorio dao) {
        this.dao = dao;
    }

    public Imovel getEntidadeImovel() {
        return entidadeImovel;
    }

    public void setEntidadeImovel(Imovel entidadeImovel) {
        this.entidadeImovel = entidadeImovel;
    }

    public Reforma getEntidade() {
        return entidade;
    }

    public void setEntidade(Reforma entidade) {
        this.entidade = entidade;
    }
     
     
}
