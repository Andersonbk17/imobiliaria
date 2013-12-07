/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IProfissaoRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Profissao;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author emerson
 */
@Stateless(name = "IProfissaoRepositorio")
public class ProfissaoDAO extends DAOGenerico<Profissao> implements IProfissaoRepositorio{
    
    public ProfissaoDAO(){
        super(Profissao.class);
    }

    @Override
    public List<Profissao> Buscar(Profissao obj) {
        // Corpo da consulta
        String consulta = "select p from Profissao p WHERE p.ativo = 1 ";

        // A parte where da consulta
        String filtro = "";

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND p.nome like '%"+obj.getNome()+"%'";

            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += " AND p.id like '%"+obj.getId()+"%'";
                
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
    public boolean Apagar(Profissao obj) {
       try {
            Query query = manager.createQuery("Update Profissao s set s.ativo = 0 WHERE s.id =:id");
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
    public boolean verificaESalva(Profissao obj) throws Exception {
        
        //Verifica se nao existe forma de pagamento com mesmo nome
        if(!this.Buscar(obj).isEmpty()){
            
            throw new Exception("Profissão já existente !");
        }else{
            return this.Salvar(obj);
        }
    }
    
}
