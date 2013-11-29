/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.FormaDePagamento;
import br.edu.ifnmg.imobiliaria.domainModel.IFormaDePagamentoRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Thaisa
 */
@Stateless(name = "IFormaDePagamentoRepositorio")
public class FormaDePagamentoDAO extends DAOGenerico<FormaDePagamento> implements IFormaDePagamentoRepositorio{

    public FormaDePagamentoDAO() {
        super(FormaDePagamento.class);
    }

    @Override
    public List<FormaDePagamento> Buscar(FormaDePagamento obj) {
    // Corpo da consulta
        String consulta = "select c from FormaDePagamento c";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
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
    public boolean Apagar(FormaDePagamento obj) {
        try {

            Query query = manager.createQuery("Update FormaDePagamento s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
