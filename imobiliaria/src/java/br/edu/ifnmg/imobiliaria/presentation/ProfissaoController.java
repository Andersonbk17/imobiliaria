/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IProfissaoRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Profissao;
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
@Named(value = "ProfissaoController")
@RequestScoped
public class ProfissaoController {

    /**
     * Creates a new instance of ProfissaoController
     */
    @EJB
    IProfissaoRepositorio dao;
    Profissao entidade;
    Profissao filtro;
    List<Profissao> listagem;
    
    public ProfissaoController() {
        entidade = new Profissao();
        filtro = new Profissao();
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
        return "CadastroProfissao.xhtml";
    }
    
    public String criar(){
        entidade = new Profissao();
        return "CadastroProfissao.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemProfissao.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemProfissao.xhtml";
    }

    
    public String voltar(){
        listagem = null;
        return "index.xhtml";
    }

    public IProfissaoRepositorio getDao() {
        return dao;
    }

    public void setDao(IProfissaoRepositorio dao) {
        this.dao = dao;
    }

    public Profissao getEntidade() {
        return entidade;
    }

    public void setEntidade(Profissao entidade) {
        this.entidade = entidade;
    }

    public Profissao getFiltro() {
        return filtro;
    }

    public void setFiltro(Profissao filtro) {
        this.filtro = filtro;
    }

    public List<Profissao> getListagem() {
        return listagem;
    }

    public void setListagem(List<Profissao> listagem) {
        this.listagem = listagem;
    }
    
}
