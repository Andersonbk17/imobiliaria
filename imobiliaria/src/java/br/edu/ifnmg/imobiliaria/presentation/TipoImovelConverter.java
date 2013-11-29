/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.ITipoDeImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.TipoDeImovel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;

/**
 *
 * @author emerson
 */
@Named(value = "tipoImovelConverter")
@SessionScoped
public class TipoImovelConverter implements Serializable, Converter {

    /**
     * Creates a new instance of TipoImovelConverter
     */
    @EJB
    ITipoDeImovelRepositorio dao;
    
    public TipoImovelConverter() {
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
            TipoDeImovel v = (TipoDeImovel)value;
            return v.getId().toString();
        } 
    }
    
}
