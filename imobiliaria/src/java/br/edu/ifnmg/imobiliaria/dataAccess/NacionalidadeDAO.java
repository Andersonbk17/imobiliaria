/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.INacionalidadeRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Nacionalidade;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "INacionalidadeRepositorio")
public class NacionalidadeDAO extends DAOGenerico<Nacionalidade> implements INacionalidadeRepositorio{

    public NacionalidadeDAO(){
        super(Nacionalidade.class);
    }
    
     @Override
    public List<Nacionalidade> Buscar(Nacionalidade obj) {
        // Corpo da consulta
        String consulta = "select n from Nacionalidade n WHERE n.id > 0 ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND n.nome like '%"+obj.getNome()+"%'";
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                filtro += " AND n.id like '%"+obj.getId()+"%'";
            }

            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta = consulta + filtro;
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
    public boolean Apagar(Nacionalidade obj) {
       try {
            Query query = manager.createQuery("Update Nacionalidade s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
