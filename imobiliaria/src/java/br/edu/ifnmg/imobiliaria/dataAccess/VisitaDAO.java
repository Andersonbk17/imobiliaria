/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IVisitaRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Visita;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IVisitaRepositorio")
public class VisitaDAO extends DAOGenerico<Visita> implements IVisitaRepositorio{

    public VisitaDAO() {
        super(Visita.class);
    }

    @Override
    public List<Visita> Buscar(Visita obj) {
        // Corpo da consulta
        String consulta = "select v from Visita v";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            /*
            if (obj.getImovel().getId()!= null && obj.getImovel().getId() > 0) {
                filtro += " v.imovel_id=:imovel ";
                parametros.put("imovel", obj.getImovel().getId());
            }*/
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " v.id=:id ";
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
    public boolean Apagar(Visita obj) {
        try {

            Query query = manager.createQuery("Update Visita s set s.ativo = 0 WHERE s.id:=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
