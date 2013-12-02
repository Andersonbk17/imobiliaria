/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.IUsuariolRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Usuario;
import java.io.Serializable;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Anderson
 */
@Named(value = "autenticacaoController")
@SessionScoped
public class AutenticacaoController implements Serializable{

    /**
     * Creates a new instance of AutenticacaoController
     */
    @EJB
    IUsuariolRepositorio dao;
    String login;
    String senha;
    Usuario usuario;
    public AutenticacaoController() {
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    public String validar() {
        try {
            usuario = dao.porLogin(login);

            if (usuario == null) {
                exibirMensagem("Login ou senha não Correspondem");
                return "login.xhtml";
            } else {
                if (senha.equals(usuario.getSenha())) {

                    HttpSession session;

                    FacesContext ctx = FacesContext.getCurrentInstance();
                    session = (HttpSession) ctx.getExternalContext().getSession(false);
                    session.setAttribute("usuarioAutenticado", usuario);

                    // AppendLog("Login");
                    return "index.xhtml";
                } else {
                    exibirMensagem("Login ou senha não correspondem");
                    return "login.xhtml";
                }
            }
        } catch (Exception ex) {
            exibirMensagem("Login ou senha não correspondem");
            ex.printStackTrace();
            return "login.xhtml";
        }

    }
    
    public Usuario pegarDaSessao() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);

        return (Usuario) session.getAttribute("usuarioAutenticado");

    }

    public String logout() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("usuarioAutenticado", null);

       // AppendLog("Logout");
        
        Enumeration<String> vals = session.getAttributeNames(); 
        
        while(vals.hasMoreElements()){
            session.removeAttribute(vals.nextElement());
        }

        return "login.xhtml";

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
