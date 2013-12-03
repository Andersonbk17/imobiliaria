/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cidade;
import br.edu.ifnmg.imobiliaria.domainModel.Estado;
import br.edu.ifnmg.imobiliaria.domainModel.ICidadeRepositorio;
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
@Named(value = "CidadeController")
@SessionScoped
public class CidadeController implements Serializable{

    /**
     * Creates a new instance of CidadeController
     */
    @EJB
    ICidadeRepositorio dao;
    Cidade entidade;
    Cidade filtro;
    Cidade f;
    List<Cidade> listagem;
    
    
    Estado estado;
    int idEstado;
    int conf;
    
    public CidadeController() {
        entidade = new Cidade();
        filtro = new Cidade();
        estado = new Estado();
        idEstado = 0;
        f = new Cidade();
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
        return "ListagemCidade.xhtml";
    }
    
    public String criar(){
        entidade = new Cidade();
        return "CadastroCidade.xhtml";
    }
    
    public String apagar(){
        dao.Apagar(entidade);
        listagem = null;
        exibirMensagem("Apagado com sucesso!");
        return "ListagemCidade.xhtml";
    }
    
    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "TipoFuncionarioListagem";
    }

    
    public String voltar(){
        return "index.xhtml";
    }
    
    public void adicionarEstado(){
        filtro.setEstado(idEstado);
        filtrarCidade();
    }
    
    public List<Cidade> filtrarCidade(){
        if(filtro.getEstado() != 0){
            f.setEstado(idEstado);
        }else{
            f.setEstado(1);
        }
        
        listagem = dao.Buscar(f);
        return listagem;
        
    }

    public ICidadeRepositorio getDao() {
        return dao;
    }

    public void setDao(ICidadeRepositorio dao) {
        this.dao = dao;
    }

    public Cidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Cidade entidade) {
        this.entidade = entidade;
    }

    public Cidade getFiltro() {
        return filtro;
    }

    public void setFiltro(Cidade filtro) {
        this.filtro = filtro;
    }

    public List<Cidade> getListagem() {
        return listagem;
    }

    public void setListagem(List<Cidade> listagem) {
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
    
    
    
    
    
    
}
