/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IImovelRepositorio")
public class ImovelDAO extends DAOGenerico<Imovel> implements IImovelRepositorio{

    public ImovelDAO() {
        super(Imovel.class);
    }

    @Override
    public List<Imovel> Buscar(Imovel obj) {
          //corpo da consuta
        String consulta = "select c from Imovel c WHERE c.ativo = 1 ";

        //A parte Where da consulta
        String filtro = "";

      
        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getCidade() != null ) {
                filtro += " AND c.cidade like '%"+obj.getCidade()+"%'";
                
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
               
                filtro += " AND c.id like '%"+obj.getId()+"%'";
               
            }
            
            if (obj.getClienteProprietario() != null ) {
               filtro += " c.clienteProprietario like '%"+obj.getClienteProprietario()+"%'";               
            }
            
            if (obj.getEnderecoRua() != null ) {
               filtro += " AND c.enderecoRua like '%"+obj.getEnderecoRua()+"%'";               
            }
            
            if (obj.getEnderecoBairro()!= null ) {
               filtro += " AND c.enderecoBairro like '%"+obj.getEnderecoBairro()+"%'";               
            }
            
            if (obj.getEnderecoCep()!= null ) {
               filtro += " AND c.enderecoCep like '%"+obj.getEnderecoCep()+"%'";               
            }
            
            if (obj.getEnderecoNumero()!= 0 ) {
               filtro += " AND c.enderecoNumero like '%"+obj.getEnderecoNumero()+"%'";               
            }
           

            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta +=  filtro;
            }
        }
        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        
        // Executa a consulta
        return query.getResultList();
    }

    @Override
    public boolean Apagar(Imovel obj) {
         try {

            Query query = manager.createQuery("Update Imovel s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
    @Override
    public boolean MudarDono(Imovel obj){
        try {

            Query query = manager.createQuery("Update Imovel s set s.clienteProprietario =:cliente WHERE s.id =:id");
            query.setParameter("cliente", obj.getClienteProprietario());
            query.setParameter("id", obj.getId());
            
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
