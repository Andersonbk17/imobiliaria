/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Caracteristica;
import br.edu.ifnmg.imobiliaria.domainModel.ICaracteristicaRepositorio;
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
@Named(value = "caracteristicaController")
@SessionScoped
public class CaracteristicaController implements Serializable{

    /**
     * Creates a new instance of CaracteristicaController
     */
    @EJB
    ICaracteristicaRepositorio dao;
    Caracteristica entidade;
    List<Caracteristica>listagem;
    Caracteristica filtro;

    public CaracteristicaController() {
        this.entidade = new Caracteristica();
      
        this.filtro = new Caracteristica();
    }
    
     public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    public void salvar(){
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com sucesso!");
        entidade = new Caracteristica();
    }
    
    public String editar(){
        return "ListagemCaracteristica.xhtml";
    }
    
    public String criar(){
        entidade = new Caracteristica();
        return "CadastradoCaracteristica.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCaracteristica.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemCaracteristica.xhtml";
    }
    
    public String voltar(){
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

    public List<Caracteristica> getListagem() {
        return listagem;
    }

    public void setListagem(List<Caracteristica> listagem) {
        this.listagem = listagem;
    }

    public Caracteristica getFiltro() {
        return filtro;
    }

    public void setFiltro(Caracteristica filtro) {
        this.filtro = filtro;
    }
    
    public List<Caracteristica> listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }
    
    
}
