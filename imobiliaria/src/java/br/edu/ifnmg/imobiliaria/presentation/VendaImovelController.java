/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IVendaImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import br.edu.ifnmg.imobiliaria.domainModel.VendaImovel;
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
@Named(value = "vendaImovel")
@RequestScoped
public class VendaImovelController {

    /**
     * Creates a new instance of VendaImovel
     */
    @EJB
    IVendaImovelRepositorio dao;
    VendaImovel entidade;
    VendaImovel filtro;
    List<VendaImovel> listagem;
    
    
    public VendaImovelController() {
        entidade = new VendaImovel();
        filtro = new VendaImovel();
    }
    
      public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        if(dao.Salvar(entidade)){
            exibirMensagem("Salvo com Sucesso!");
        }else{
            exibirMensagem("Erro ao Salvar !");
        }
        listagem = null;

    }

    public String editar() {
        return "CadastroVendaImovel.xhtml";
    }

    public String criar() {
        entidade = new VendaImovel();
        return "CadastroVendaImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "VendaImovelListagem.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "index.xhtml";
    }
    
    public List<VendaImovel>listarTodos(){
        listagem = dao.Buscar(filtro);
        return listagem;
    }
    
     public String filtrar() {
       // listagem = dao.Buscar(filtro);
        return "ListagemVendaImovel.xhtml";
    }

    public IVendaImovelRepositorio getDao() {
        return dao;
    }

    public void setDao(IVendaImovelRepositorio dao) {
        this.dao = dao;
    }

    public VendaImovel getEntidade() {
        return entidade;
    }

    public void setEntidade(VendaImovel entidade) {
        this.entidade = entidade;
    }

    public VendaImovel getFiltro() {
        return filtro;
    }

    public void setFiltro(VendaImovel filtro) {
        this.filtro = filtro;
    }

    public List<VendaImovel> getListagem() {
        return listagem;
    }

    public void setListagem(List<VendaImovel> listagem) {
        this.listagem = listagem;
    }
         
}
