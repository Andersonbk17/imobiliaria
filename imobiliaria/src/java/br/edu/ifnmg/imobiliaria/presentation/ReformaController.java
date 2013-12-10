/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IReformaRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import br.edu.ifnmg.imobiliaria.domainModel.Reforma;
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
@Named(value = "reformaController")
@SessionScoped
public class ReformaController implements Serializable{

    /**
     * Creates a new instance of ReformaController
     */
    
    @EJB
    IReformaRepositorio dao;
    Imovel entidadeImovel;
    Reforma filtro;
    Reforma entidade;
    List<Reforma> listagem;
    
    public ReformaController() {
        entidade = new Reforma();
        entidadeImovel = new Imovel();
        filtro = new Reforma();
    }
    
    
      public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
        if(dao.Salvar(entidade)){
            exibirMensagem("Salvo com Sucesso!");
            entidade = new Reforma();
        }else{
            exibirMensagem("Erro ao salvar !");
        }
    
    }

    public String editar() {
        return "CadastroReforma.xhtml";
    }

    public String criar() {
        entidade = new Reforma();
        return "CadastroReforma.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
      listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemReforma.xhtml";
    }

    public String voltar() {
      listagem = null;
        return "ListagemReforma.xhtml";
    }
    
     public String filtrar() {
     listagem = dao.Buscar(filtro);
     return "ListagemReforma.xhtml";
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

    public Reforma getFiltro() {
        return filtro;
    }

    public void setFiltro(Reforma filtro) {
        this.filtro = filtro;
    }

    public List<Reforma> getListagem() {
        return listagem;
    }

    public void setListagem(List<Reforma> listagem) {
        this.listagem = listagem;
    }
     
    
     
}
