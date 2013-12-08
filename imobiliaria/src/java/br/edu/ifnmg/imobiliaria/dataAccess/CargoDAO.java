/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.Cargo;
import br.edu.ifnmg.imobiliaria.domainModel.ICargoRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "ICargoRepositorio")
public class CargoDAO extends DAOGenerico<Cargo> implements ICargoRepositorio{

    public CargoDAO() {
        super(Cargo.class);
    }

    @Override
    public List<Cargo> Buscar(Cargo obj) {
        // Corpo da consulta
        String consulta = "select c from Cargo c WHERE c.ativo = 1 ";

        // A parte where da consulta
        String filtro = "";



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
    public boolean Apagar(Cargo obj) {
       try {
            Query query = manager.createQuery("Update Cargo s set s.ativo = 0 WHERE s.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
   
    @Override
    public boolean verificaESalva(Cargo obj) throws Exception {
        
        //Verifica se nao existe cargo com mesmo nome
        if(!this.Buscar(obj).isEmpty()){
            
            throw new Exception("Cargo já existente !");
        }else{
            return this.Salvar(obj);
        }
    }
    
    
}
