/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.ITipoDeImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.TipoDeImovel;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "ITipoDeImovelRepositorio")
public class TipoDeImovelDAO extends DAOGenerico<TipoDeImovel> implements ITipoDeImovelRepositorio{

    public TipoDeImovelDAO() {
        super(TipoDeImovel.class);
    }

    @Override
    public List<TipoDeImovel> Buscar(TipoDeImovel obj) {
         //corpo da consuta
        String consulta = "select c from TipoDeImovel c ";

        //A parte Where da consulta
        String filtro = "";

        //Guarda a lista de parametros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " c.nome=:nome ";
                parametros.put("nome", obj.getNome());
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " c.id=:id";
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

        //JOptionPane.showMessageDialog(null,query);
        // Executa a consulta
        return query.getResultList();
    }

    @Override
    public boolean Apagar(TipoDeImovel obj) {
     
        try {

            Query query = manager.createQuery("Update TipoDeImovel s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
