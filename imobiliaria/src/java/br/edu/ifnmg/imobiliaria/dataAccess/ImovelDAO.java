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
        String consulta = "select c from Imovel c ";

        //A parte Where da consulta
        String filtro = "";

        //Guarda a lista de parametros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getCidade() != null ) {
                filtro += " c.nome=:nome ";
                parametros.put("nome", obj.getCidade());
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " c.id=:id";
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

        //JOptionPane.showMessageDialog(null,query);
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
