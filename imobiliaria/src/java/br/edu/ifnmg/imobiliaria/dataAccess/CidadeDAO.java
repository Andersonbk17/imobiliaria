/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.Cidade;
import br.edu.ifnmg.imobiliaria.domainModel.ICidadeRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "ICidadeRepositorio")
public class CidadeDAO extends DAOGenerico<Cidade> implements  ICidadeRepositorio{

    public CidadeDAO() {
        super(Cidade.class);
    }

    @Override
    public List<Cidade> Buscar(Cidade obj) {
        // Corpo da consulta
        String consulta = "select c from Cidade c where c.id > 0 ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND c.nome like '%"+obj.getNome()+"%'";
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                filtro += " AND c.id like '%"+obj.getId()+"%'";
            }
            
            if (obj.getEstado()!= 0 && obj.getEstado()> 0) {
                filtro += " AND c.estado like '%"+obj.getEstado()+"%'";
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
    public boolean Apagar(Cidade obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
