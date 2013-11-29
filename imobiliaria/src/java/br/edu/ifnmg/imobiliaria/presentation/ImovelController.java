/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cidade;
import br.edu.ifnmg.imobiliaria.domainModel.Cliente;
import br.edu.ifnmg.imobiliaria.domainModel.FormaDePagamento;
import br.edu.ifnmg.imobiliaria.domainModel.IImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import br.edu.ifnmg.imobiliaria.domainModel.TipoDeImovel;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Anderson
 */
@Named(value = "imovelController")
@RequestScoped
public class ImovelController{

    /**
     * Creates a new instance of ImovelController
     */
    
   @EJB
   IImovelRepositorio dao;
   
   Imovel entidade;
   Imovel filtro;
   List<Imovel> listagem;

    public ImovelController() {
        this.filtro = new Imovel();
        listagem = null;
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
        return "ListagemImovel.xhtml";
    }
    
    public String criar(){
        entidade = new Imovel();
        return "CadastroImovel.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCargo.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemImovel.xhtml";
    }
    
    public List<Imovel> listarTodos(){
         listagem = dao.Buscar(filtro);
         return listagem;
     }
    
    public String voltar(){
        listagem = null;
        return "ListagemImovel.xhtml";
    }
    
    
    public IImovelRepositorio getDao() {
        return dao;
    }

    public void setDao(IImovelRepositorio dao) {
        this.dao = dao;
    }

   
    public Imovel getEntidade() {
        return entidade;
    }

    public void setEntidade(Imovel entidade) {
        this.entidade = entidade;
    }

    public Imovel getFiltro() {
        return filtro;
    }

    public void setFiltro(Imovel filtro) {
        this.filtro = filtro;
    }

    public List<Imovel> getListagem() {
        return listagem;
    }

    public void setListagem(List<Imovel> listagem) {
        this.listagem = listagem;
    }
    
    
}
