/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.Cliente;
import br.edu.ifnmg.imobiliaria.domainModel.IClienteRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "IClienteRepositorio")
public class ClienteDAO extends DAOGenerico<Cliente> implements IClienteRepositorio{

    public ClienteDAO() {
        super(Cliente.class);
    }

    @Override
    public List<Cliente> Buscar(Cliente obj) {
        // Corpo da consulta
        String consulta = "select c from Cliente c WHERE c.ativo = 1 ";

        // A parte where da consulta
        String filtro = "";


        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " c.nome like '%"+obj.getNome()+"%' ";
                //parametros.put("nome", obj.getNome());
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += " c.id ="+obj.getId();

            }
            //Cpf
            if (obj.getCpf() != null && obj.getCpf().length() > 0) {
                
                filtro += " c.cpf="+obj.getCpf();
                
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
    public boolean Apagar(Cliente obj) {
       try {
            Query query = manager.createQuery("Update Cliente s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
