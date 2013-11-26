/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.ITipoDeImovel;
import br.edu.ifnmg.imobiliaria.domainModel.TipoDeImovel;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
public class TipoDeImovelDAO extends DAOGenerico<TipoDeImovel> implements ITipoDeImovel{

    public TipoDeImovelDAO() {
        super(TipoDeImovel.class);
    }

    @Override
    public List<TipoDeImovel> Buscar(TipoDeImovel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(TipoDeImovel obj) {
        
        try{
         
            
            
             Query query = manager.createQuery("Update TipoDeImovel s set s.ativo = 0 WHERE s.id :=id");
             query.setParameter("id", obj.getId());
             query.executeUpdate();
             
        
             return true;
        
            
        }catch(Exception ex){
           ex.printStackTrace();
        
            return false;
        }
    }
    
}
