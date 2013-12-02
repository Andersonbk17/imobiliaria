/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Cidade;
import br.edu.ifnmg.imobiliaria.domainModel.ICidadeRepositorio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author emerson
 */
@Named(value = "cidadeConverter")
@SessionScoped
public class CidadeConverter implements Serializable, Converter {

    /**
     * Creates a new instance of CidadeConverter
     */
    @EJB
    ICidadeRepositorio dao;
    
    public CidadeConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return dao.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")){
            return "";
        } else{
            Cidade c = (Cidade)value;
            return c.getId().toString();
        } 
    }
    
}
