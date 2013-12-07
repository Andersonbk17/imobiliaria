/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IReformaRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Reforma;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IReformaRepositorio")
public class ReformaDAO extends DAOGenerico<Reforma> implements IReformaRepositorio{

    public ReformaDAO() {
        super(Reforma.class);
    }

    @Override
    public List<Reforma> Buscar(Reforma obj) {
         //corpo da consuta
        String consulta = "select c from Reforma c where c.ativo = 1 ";

        //A parte Where da consulta
        String filtro = "";


        // Verifica campo por campo os valores que serão filtradoss
        if (obj != null) {
            //Nome
            if (obj.getDataInicio() != null) {
                filtro += " AND c.dataInicio like '%"+obj.getDataInicio()+"%'";
            }
            
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += "AND c.id ="+obj.getId();
           //     parametros.put("id", obj.getId());
            }
           

            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta += filtro;
            }
        }
        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Executa a consulta
        return query.getResultList();
    }

    @Override
    public boolean Apagar(Reforma obj) {
         try {

            Query query = manager.createQuery("Update Reforma s set s.ativo = 0 WHERE s.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
