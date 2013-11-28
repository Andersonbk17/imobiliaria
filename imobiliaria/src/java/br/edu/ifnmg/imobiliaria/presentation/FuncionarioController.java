/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Funcionario;
import br.edu.ifnmg.imobiliaria.domainModel.IFuncionarioRepositorio;
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
@Named(value = "FuncionarioController")
@RequestScoped
public class FuncionarioController {

    /**
     * Creates a new instance of FuncionarioController
     */
    @EJB
    IFuncionarioRepositorio dao;
    Funcionario entidade;
    Funcionario filtro;
    List<Funcionario> listagem;
    
    
    public FuncionarioController() {
        entidade = new Funcionario();
        filtro = new Funcionario();
       
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
        return "CadastroFuncionario.xhtml";
    }
    
    public String criar(){
        entidade = new Funcionario();
        return "CadastroFuncionario.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemFuncionario.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "FuncionarioListagem.xhtml";
    }
    
    public String voltar(){
        return "index.xhtml";
    }
    
    
    public List<Funcionario> listarFuncionario(){
        listagem = dao.Buscar(filtro);
        return listagem;
    }
    
    public IFuncionarioRepositorio getDao() {
        return dao;
    }

    public void setDao(IFuncionarioRepositorio dao) {
        this.dao = dao;
    }

    public Funcionario getEntidade() {
        return entidade;
    }

    public void setEntidade(Funcionario entidade) {
        this.entidade = entidade;
    }

    public Funcionario getFiltro() {
        return filtro;
    }

    public void setFiltro(Funcionario filtro) {
        this.filtro = filtro;
    }

    public List<Funcionario> getListagem() {
        return listagem;
    }

    public void setListagem(List<Funcionario> listagem) {
        this.listagem = listagem;
    }
    
    
     
    
}
