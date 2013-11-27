/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cargo;
import br.edu.ifnmg.imobiliaria.domainModel.ICargoRepositorio;
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
@Named(value = "CargoController")
@RequestScoped
public class CargoController {

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
        context.addMessage(null, new FacesMessage(msg));
    }
    
    public void salvar(){
        dao.Salvar(entidade);
        listagem = null;
        exibirMensagem("Salvo com sucesso!");
    }
    
    public String editar(){
        return "ListagemCargo.xhtml";
    }
    
    public String criar(){
        entidade = new Cargo();
        return "CadastradoCargo.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCargo.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "CargoListagem.xhtml";
    }

    
    public String voltar(){
        listagem = null;
        return "index.xhtml";
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
