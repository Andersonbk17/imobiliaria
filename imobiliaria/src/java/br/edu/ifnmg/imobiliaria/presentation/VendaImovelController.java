/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cliente;
import br.edu.ifnmg.imobiliaria.domainModel.FormaDePagamento;
import br.edu.ifnmg.imobiliaria.domainModel.IClienteRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.IFormaDePagamentoRepositorio;
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
    Imovel filtro;
    List<Imovel> listagem;
    @EJB
    IFormaDePagamentoRepositorio daoFormadePagamento;
    FormaDePagamento entidadeFormaDepagamento;
    @EJB
    IClienteRepositorio daoCliente;
    Cliente clienteVendedor;
    Cliente clienteComprador;
    List<Cliente> listaClientes;
    List<FormaDePagamento>listaFormaPagamento;
    
    
    public VendaImovelController() {
        entidade = new VendaImovel();
        filtro = new Imovel();
        clienteComprador = null;
        clienteVendedor = null;
        listaClientes = null;
        entidadeFormaDepagamento = null;
        entidadeFormaDepagamento = new FormaDePagamento();
        listarFormasDePagamento();
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
        return "CadastroTipoDeImovel.xhtml";
    }

    public String criar() {
        entidade = new VendaImovel();
        return "CadastroTipoDeImovel.xhtml";
    }

    public String apagar() {
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "TipoImovelListagem.xhtml";
    }

    public String voltar() {
        listagem = null;
        return "index.xhtml";
    }
    
     public String filtrar() {
       // listagem = dao.Buscar(filtro);
        return "ListagemTipoDeImovel.xhtml";
    }
     
     public void listarFormasDePagamento(){
         listaFormaPagamento = daoFormadePagamento.Buscar(entidadeFormaDepagamento);
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

    public IFormaDePagamentoRepositorio getDaoFormadePagamento() {
        return daoFormadePagamento;
    }

    public void setDaoFormadePagamento(IFormaDePagamentoRepositorio daoFormadePagamento) {
        this.daoFormadePagamento = daoFormadePagamento;
    }

    public FormaDePagamento getEntidadeFormaDepagamento() {
        return entidadeFormaDepagamento;
    }

    public void setEntidadeFormaDepagamento(FormaDePagamento entidadeFormaDepagamento) {
        this.entidadeFormaDepagamento = entidadeFormaDepagamento;
    }

    public IClienteRepositorio getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(IClienteRepositorio daoCliente) {
        this.daoCliente = daoCliente;
    }

    public Cliente getClienteVendedor() {
        return clienteVendedor;
    }

    public void setClienteVendedor(Cliente clienteVendedor) {
        this.clienteVendedor = clienteVendedor;
    }

    public Cliente getClienteComprador() {
        return clienteComprador;
    }

    public void setClienteComprador(Cliente clienteComprador) {
        this.clienteComprador = clienteComprador;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<FormaDePagamento> getListaFormaPagamento() {
        return listaFormaPagamento;
    }

    public void setListaFormaPagamento(List<FormaDePagamento> listaFormaPagamento) {
        this.listaFormaPagamento = listaFormaPagamento;
    }
    
    
}
