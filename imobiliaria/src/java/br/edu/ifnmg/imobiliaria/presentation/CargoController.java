/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cargo;
import br.edu.ifnmg.imobiliaria.domainModel.ICargoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author emerson
 */
@Named(value = "CargoController")
@SessionScoped
public class CargoController implements Serializable{

    /**
     * Creates a new instance of CargoController
     */
    @EJB
    ICargoRepositorio dao;
    Cargo entidade;
    Cargo filtro;
    List<Cargo> listagem;
    
    public CargoController() {
        entidade = new Cargo();
        filtro = new Cargo();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }
    
    public void salvar(){
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com sucesso!");
    }
    
    public String editar(){
        return "CadastroCargo.xhtml";
    }
    
    public String criar(){
        entidade = new Cargo();
        return "CadastroCargo.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCargo.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemCargo.xhtml";
    }

    
    public String voltar(){
        listagem = null;
        return "ListagemCargo.xhtml";
    }
    
    public List<Cargo> listarCargos() {
        listagem = dao.Buscar(filtro);
        return listagem;
    }

    public ICargoRepositorio getDao() {
        return dao;
    }

    public void setDao(ICargoRepositorio dao) {
        this.dao = dao;
    }

    public Cargo getEntidade() {
        return entidade;
    }

    public void setEntidade(Cargo entidade) {
        this.entidade = entidade;
    }

    public Cargo getFiltro() {
        return filtro;
    }

    public void setFiltro(Cargo filtro) {
        this.filtro = filtro;
    }

    public List<Cargo> getListagem() {
        return listagem;
    }

    public void setListagem(List<Cargo> listagem) {
        this.listagem = listagem;
    }
    
    
    
}
