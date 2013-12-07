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
        String consulta = "select c from TipoDeImovel c where c.ativo = 1 ";

        //A parte Where da consulta
        String filtro = "";


        // Verifica campo por campo os valores que serão filtradoss
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND c.nome like '%"+obj.getNome()+"%'";
            }
            
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += "AND c.id ="+obj.getId();
           //     parametros.put("id", obj.getId());
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
    public boolean Apagar(TipoDeImovel obj) {
     
        try {

            Query query = manager.createQuery("Update TipoDeImovel s set s.ativo = 0 WHERE s.id =:id");
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
    public boolean verificaESalva(TipoDeImovel obj) throws Exception {
        
        //Verifica se nao existe forma de pagamento com mesmo nome
        if(!this.Buscar(obj).isEmpty()){
            
            throw new Exception("Tipo de imóvel já existente !");
        }else{
            return this.Salvar(obj);
        }
    }
    
}
