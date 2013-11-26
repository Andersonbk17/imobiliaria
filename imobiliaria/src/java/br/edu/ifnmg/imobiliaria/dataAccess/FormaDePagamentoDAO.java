/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.FormaDePagamento;
import br.edu.ifnmg.imobiliaria.domainModel.IFormaDePagamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Thaisa
 */
@Stateless(name = "IFormaDePagamento")
public class FormaDePagamentoDAO extends DAOGenerico<FormaDePagamento> implements IFormaDePagamento{

    public FormaDePagamentoDAO() {
        super(FormaDePagamento.class);
    }

    @Override
    public List<FormaDePagamento> Buscar(FormaDePagamento obj) {
        

throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.






/*

// Corpo da consulta
        EntityTransaction transacao = manager.getTransaction();
        try {

            String consulta = "";
            if (obj.getId() != null) {
                 consulta = "Select s from FormaDePagamento s Where s.ativo = 1 and s.id like '%" + obj.getId() + "%'";
                 
             }else if(obj.getNome() != null){
                 consulta = "Select s from FormaDePagamento s Where s.ativo = 1 and s.nome like '%" + obj.getNome() + "%'";
             }
            transacao.begin();
            // Cria a consulta no JPA
            Query query = manager.createQuery(consulta);

           
            transacao.commit();
            return query.getResultList();
        } catch (Exception ex) {

            ex.printStackTrace();
            transacao.rollback();

            return null;
        }
        */
    }

    @Override
    public boolean Apagar(FormaDePagamento obj) {
       // EntityTransaction transacao = manager.getTransaction();
        try{
         //   transacao.begin();
            String consulta = "Update FormaDeTagamento s set s.ativo = 0 WHERE s.id ="+obj.getId();
            
             Query query = manager.createQuery(consulta);
             query.executeUpdate();
             
           //  transacao.commit();
             return true;
        
            
        }catch(Exception ex){
           ex.printStackTrace();
           //transacao.rollback();
            return false;
        }
    }
    
}
