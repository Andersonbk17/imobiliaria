/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Foto;
import br.edu.ifnmg.imobiliaria.domainModel.IImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Anderson
 */
@Named(value = "imovelController")
@SessionScoped
public class ImovelController implements Serializable{

    /**
     * Creates a new instance of ImovelController
     */
    @EJB
    IImovelRepositorio dao;

    Imovel entidade;
    Imovel filtro;
    List<Imovel> listagem;
    Foto foto;

    public ImovelController() {
        this.filtro = new Imovel();
        listagem = null;
        entidade = new Imovel();
        foto = new Foto();
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }

    public void salvar() {
        if(dao.Salvar(entidade)){
            listagem = null;
            entidade = new Imovel();
            exibirMensagem("Salvo com Sucesso !");
        }else{
            exibirMensagem("Erro ao salvar!");
        }
        
        
    }
    
     public List<Imovel> autoCompletar(String tmp){
        Imovel c = new Imovel();
        c.setId(Long.parseLong(tmp));
        return dao.Buscar(c);
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
        return "ListagemCargo.xhtml";
    }

    public String filtrar() {
        listagem = dao.Buscar(filtro);
        return "ListagemImovel.xhtml";
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {  
       // FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        //FacesContext.getCurrentInstance().addMessage(null, msg);  
        exibirMensagem(event.getFile().getFileName() );
    }  
    
    
    

    public void addFoto(){
        entidade.addFoto(foto);
        foto = new Foto();
    }
    public List<Imovel> listarTodos() {
        listagem = dao.Buscar(filtro);
        return listagem;
    }

    public String voltar() {
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
