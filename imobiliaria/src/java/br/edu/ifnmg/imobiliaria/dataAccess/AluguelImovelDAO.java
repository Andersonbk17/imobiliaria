/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.AluguelImovel;
import br.edu.ifnmg.imobiliaria.domainModel.IAluguelImovelRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IAluguelImovelRepositorio")
public class AluguelImovelDAO extends DAOGenerico<AluguelImovel> implements IAluguelImovelRepositorio{

    public AluguelImovelDAO() {
        super(AluguelImovel.class);
    }

    @Override
    public List<AluguelImovel> Buscar(AluguelImovel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(AluguelImovel obj) {
       
        try{
       
            String consulta = "Update AluguelImovel s set s.ativo = 0 WHERE s.id ="+obj.getId();
            
             Query query = manager.createQuery(consulta);
             query.executeUpdate();
             
       
             return true;
        
            
        }catch(Exception ex){
           ex.printStackTrace();
       
            return false;
        }
    }
    
}
