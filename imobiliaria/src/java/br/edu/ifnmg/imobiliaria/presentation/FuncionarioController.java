/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Funcionario;
import br.edu.ifnmg.imobiliaria.domainModel.IFuncionarioRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

/**
 *
 * @author emerson
 */
@Named(value = "FuncionarioController")
@SessionScoped
public class FuncionarioController implements Serializable{

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
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }
    
    public void salvar(){
        if(dao.Salvar(entidade)){
            listagem = null;
            exibirMensagem("Salvo com sucesso!");
            entidade = new Funcionario();
        }else{
            exibirMensagem("Erro ao Salvar!");
        }
        
    }
    
     public List<Funcionario> autoCompletar(String tmp){
        Funcionario c = new Funcionario();
        c.setNome(tmp);
        return dao.Buscar(c);
    }
    
    public String editar(){
       return "CadastroFuncionario.xhtml";
    }
    
    public String criar(){
        listagem = null;
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
        return "ListagemFuncionario.xhtml";
    }
    
    public String voltar(){
        listagem = null;
        return "ListagemFuncionario.xhtml";
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
