/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IProfissaoRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Profissao;
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
 * @author emerson
 */
@Named(value = "ProfissaoController")
@SessionScoped
public class ProfissaoController implements Serializable{

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
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }
    
    public void salvar(){
        try {
            if(dao.verificaESalva(entidade)){
                listagem = null;
                exibirMensagem("Salvo com sucesso!");
                entidade = new Profissao();
            }else{
                exibirMensagem("Erro ao Salvar!");
            }
        } catch (Exception ex) {
            exibirMensagem(ex.getMessage());
        }
        
    }
    
    public String editar(){
        //listagem = null;
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
        return "ListagemProfissao.xhtml";
    }
    
    public List<Profissao> listarProfissao(){
        listagem = dao.Buscar(filtro);
        return listagem;
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
