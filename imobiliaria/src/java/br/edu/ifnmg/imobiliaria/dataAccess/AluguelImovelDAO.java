/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.AluguelImovel;
import br.edu.ifnmg.imobiliaria.domainModel.IAluguelImovelRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IAluguelImovelRepositorio")
public class AluguelImovelDAO extends DAOGenerico<AluguelImovel> implements IAluguelImovelRepositorio{

    public AluguelImovelDAO() {
        super(AluguelImovel.class);
    }

    @Override
    public List<AluguelImovel> Buscar(AluguelImovel obj) {
        // Corpo da consulta
        String consulta = "select c from AluguelImovel c";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " c.id=:id ";
                parametros.put("id", obj.getId());
            }
         
            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta = consulta + " where " + filtro;
            }
        }

        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        for (String par : parametros.keySet()) {
            query.setParameter(par, parametros.get(par));
        }

        // Executa a consulta
        return query.getResultList();
    }

    @Override
    public boolean Apagar(AluguelImovel obj) {
       
        try{
       
            String consulta = "Update AluguelImovel s set s.ativo = 0 WHERE s.id ="+obj.getId();
            
             Query query = manager.createQuery(consulta);
             query.executeUpdate();
             
       
             return true;
        
            
        }catch(Exception ex){
           ex.printStackTrace();
       
            return false;
        }
    }
    
}
