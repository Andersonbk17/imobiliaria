/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.Funcionario;
import br.edu.ifnmg.imobiliaria.domainModel.IFuncionarioRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "IFuncionarioRepositorio")
public class FuncionarioDAO extends DAOGenerico<Funcionario> implements IFuncionarioRepositorio{

    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> Buscar(Funcionario obj) {
         // Corpo da consulta
        String consulta = "select f from Funcionario f WHERE f.ativo = 1 ";

        // A parte where da consulta
        String filtro = "";


        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND f.nome like '%"+obj.getNome()+"%' ";
              
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += " AND f.id like '%"+obj.getId()+"%'";
                
            }
            //Cpf
            if (obj.getCpf() != null && obj.getCpf().length() > 0) {
                
                filtro += " AND f.cpf like '%"+obj.getCpf()+"%'";
                
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
    public boolean Apagar(Funcionario obj) {
        try {
            Query query = manager.createQuery("Update Funcionario s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
