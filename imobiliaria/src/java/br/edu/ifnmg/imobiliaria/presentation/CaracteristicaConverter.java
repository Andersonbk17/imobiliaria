/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.presentation;

import br.edu.ifnmg.imobiliaria.domainModel.Caracteristica;
import br.edu.ifnmg.imobiliaria.domainModel.ICaracteristicaRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Anderson
 */
@Named(value = "caracteristicaConverter")
@SessionScoped
public class CaracteristicaConverter implements Serializable,Converter {

    /**
     * Creates a new instance of CaracteristicaConverter
     */
     @EJB
     ICaracteristicaRepositorio dao;
    
    public CaracteristicaConverter(){
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
            Caracteristica c = (Caracteristica)value;
            return c.getId().toString();
        } 
    }
    
    
}
