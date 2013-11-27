/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cidade;
import br.edu.ifnmg.imobiliaria.domainModel.Estado;
import br.edu.ifnmg.imobiliaria.domainModel.ICidadeRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.IImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.ITipoDeImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import br.edu.ifnmg.imobiliaria.domainModel.TipoDeImovel;
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
    Estado estado;
    int idEstado;
    
    @EJB
    ICidadeRepositorio daoCidade;
    List<Cidade> listaCidades;
    Cidade cidadeFiltro;
    Cidade entidadeCidade;
    
    @EJB
    ITipoDeImovelRepositorio daoTipoImovel;
    TipoDeImovel entidadeTipoImovel;
    List<TipoDeImovel> listaTipoImovel;
    
    
    public ImovelController() {
        entidade = new Imovel();
        filtro = new Imovel();
        estado = new Estado();
        cidadeFiltro = new Cidade();
        entidadeTipoImovel = new TipoDeImovel();
        //listarTipoImveis();
        
        //verificar 
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
    
    public void adicionarEstado(){
        cidadeFiltro.setEstado(idEstado);
        filtrarCidade();
    }

    public String editar() {
        return "CadastroImovel.xhtml";
    }

    public String criar() {
        entidade = new Imovel();
        return "CadastroImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ImovelListagem.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "index.xhtml";
    }
    
    public void listarTipoImveis(){
        listaTipoImovel = daoTipoImovel.Buscar(entidadeTipoImovel);
    }
    
     public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemImovel.xhtml";
    }
     
     public void filtrarCidade(){
         listaCidades = daoCidade.Buscar(cidadeFiltro);
         
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public ICidadeRepositorio getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(ICidadeRepositorio daoCidade) {
        this.daoCidade = daoCidade;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public Cidade getCidadeFiltro() {
        return cidadeFiltro;
    }

    public void setCidadeFiltro(Cidade cidadeFiltro) {
        this.cidadeFiltro = cidadeFiltro;
    }

    public Cidade getEntidadeCidade() {
        return entidadeCidade;
    }

    public void setEntidadeCidade(Cidade entidadeCidade) {
        this.entidadeCidade = entidadeCidade;
    }

    public ITipoDeImovelRepositorio getDaoTipoImovel() {
        return daoTipoImovel;
    }

    public void setDaoTipoImovel(ITipoDeImovelRepositorio daoTipoImovel) {
        this.daoTipoImovel = daoTipoImovel;
    }

    public TipoDeImovel getEntidadeTipoImovel() {
        return entidadeTipoImovel;
    }

    public void setEntidadeTipoImovel(TipoDeImovel entidadeTipoImovel) {
        this.entidadeTipoImovel = entidadeTipoImovel;
    }

    public List<TipoDeImovel> getListaTipoImovel() {
        return listaTipoImovel;
    }

    public void setListaTipoImovel(List<TipoDeImovel> listaTipoImovel) {
        this.listaTipoImovel = listaTipoImovel;
    }
    
    
    
}
