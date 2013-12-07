/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IVendaImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.VendaImovel;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IVendaImovelRepositorio")
public class VendaImovelDAO extends DAOGenerico<VendaImovel> implements IVendaImovelRepositorio{

    public VendaImovelDAO() {
        super(VendaImovel.class);
    }

    @Override
    public List<VendaImovel> Buscar(VendaImovel obj) {
         // Corpo da consulta
        String consulta = "select c from VendaImovel c WHERE c.ativo = 1 ";

        // A parte where da consulta
        String filtro = "";

       
       
        if (obj != null) {
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
       
                filtro += " AND c.id like '%"+obj.getId()+"%' ";
       
            }
            
            if (obj.getClienteComprador() != null ) {
       
                filtro += " AND c.clienteComprador like '%"+obj.getClienteComprador()+"%' ";
       
            }
            if (obj.getClienteVendedor() != null ) {
       
                filtro += " AND c.clienteVendedor like '%"+obj.getClienteVendedor()+"%' ";
       
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
    public boolean Apagar(VendaImovel obj) {
        try {

            Query query = manager.createQuery("Update VendaImovel s set s.ativo = 0 WHERE s.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
    
}
