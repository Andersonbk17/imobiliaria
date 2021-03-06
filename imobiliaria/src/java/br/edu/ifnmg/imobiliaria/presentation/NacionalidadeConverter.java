/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.INacionalidadeRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Nacionalidade;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author emerson
 */
@Named(value = "nacionalidadeConverter")
@SessionScoped
public class NacionalidadeConverter implements Serializable, Converter {

    /**
     * Creates a new instance of NacionalidadeConverter
     */
    @EJB
    INacionalidadeRepositorio dao;
    
    public NacionalidadeConverter() {
        
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
            Nacionalidade n = (Nacionalidade)value;
            return n.getId().toString();
        } 
    }
    
    
    
}
