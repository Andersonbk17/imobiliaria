/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.AluguelImovel;
import br.edu.ifnmg.imobiliaria.domainModel.IAluguelImovelRepositorio;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Anderson
 */
@Named(value = "aluguelImovelController")
@RequestScoped
public class AluguelImovelController {

    /**
     * Creates a new instance of AluguelImovelController
     */
    @EJB
    IAluguelImovelRepositorio dao;
    AluguelImovel entidade;
    AluguelImovel filtro;
    List<AluguelImovel> listagem;
    public AluguelImovelController() {
        entidade = new AluguelImovel();
        filtro = new AluguelImovel();
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
        return "CadastroAluguelImovel.xhtml";
    }

    public String criar() {
        entidade = new AluguelImovel();
        return "CadastroAluguelImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemAluguelImovel.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "index.xhtml";
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemAluguelImovel.xhtml";
    }
     
     public List<AluguelImovel>listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }

    public IAluguelImovelRepositorio getDao() {
        return dao;
    }

    public void setDao(IAluguelImovelRepositorio dao) {
        this.dao = dao;
    }

    public AluguelImovel getEntidade() {
        return entidade;
    }

    public void setEntidade(AluguelImovel entidade) {
        this.entidade = entidade;
    }

    public AluguelImovel getFiltro() {
        return filtro;
    }

    public void setFiltro(AluguelImovel filtro) {
        this.filtro = filtro;
    }

    public List<AluguelImovel> getListagem() {
        return listagem;
    }

    public void setListagem(List<AluguelImovel> listagem) {
        this.listagem = listagem;
    }
     
     
    
}
