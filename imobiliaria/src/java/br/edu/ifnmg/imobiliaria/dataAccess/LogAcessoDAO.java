/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.ILogAcessoRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.LogAcesso;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "ILogAcessoRepositorio")
public class LogAcessoDAO extends DAOGenerico<LogAcesso> implements ILogAcessoRepositorio{

    public LogAcessoDAO() {
        super(LogAcesso.class);
    }

     @Override
    public List<LogAcesso> Buscar(LogAcesso obj) {
        // Corpo da consulta
        String consulta = "select l from LogAcesso l";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getUsuario().getLogin() != null && obj.getUsuario().getLogin().length() > 0) {
                filtro += " l.usuario_id like user ";
                parametros.put("user", obj.getUsuario());
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " l.id like id";
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
    public boolean Apagar(LogAcesso obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
