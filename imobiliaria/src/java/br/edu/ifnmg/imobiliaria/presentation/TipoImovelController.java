/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

//import javax.faces.bean.SessionScoped;
import br.edu.ifnmg.imobiliaria.domainModel.ITipoDeImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.TipoDeImovel;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anderson
 */

@Named(value = "TipoImovelController")
@SessionScoped
public class TipoImovelController implements Serializable{
    
    @EJB
    ITipoDeImovelRepositorio dao;
    TipoDeImovel entidade;
    TipoDeImovel filtro;
    List<TipoDeImovel> listagem;

    public TipoImovelController() {
        entidade = new TipoDeImovel();
        filtro = new TipoDeImovel();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
        try {
            if(dao.verificaESalva(entidade)){
                listagem = null;
                exibirMensagem("Salvo com Sucesso!");
                entidade = new TipoDeImovel();
                
            }else{
                exibirMensagem("Erro ao Salvar!");
            }
        } catch (Exception ex) {
            exibirMensagem(ex.getMessage());
        }
    }

    public String editar() {
        return "CadastroTipoDeImovel.xhtml";
    }

    public String criar() {
        entidade = new TipoDeImovel();
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
        return "ListagemTipoDeImovel.xhtml";
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemTipoDeImovel.xhtml";
    }
     
     public List<TipoDeImovel> listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }
    public ITipoDeImovelRepositorio getDao() {
        return dao;
    }

    public void setDao(ITipoDeImovelRepositorio dao) {
        this.dao = dao;
    }

    public TipoDeImovel getEntidade() {
        return entidade;
    }

    public void setEntidade(TipoDeImovel entidade) {
        this.entidade = entidade;
    }

    public TipoDeImovel getFiltro() {
        return filtro;
    }

    public void setFiltro(TipoDeImovel filtro) {
        this.filtro = filtro;
    }

    public List<TipoDeImovel> getListagem() {
        return listagem;
    }

    public void setListagem(List<TipoDeImovel> listagem) {
        this.listagem = listagem;
    }

    
    
    
}
