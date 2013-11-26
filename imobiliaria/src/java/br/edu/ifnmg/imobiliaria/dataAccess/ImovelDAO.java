/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.Imovel;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
public class ImovelDAO extends DAOGenerico<Imovel> implements IRepositorio<Imovel>{

    public ImovelDAO() {
        super(Imovel.class);
    }

    @Override
    public List<Imovel> Buscar(Imovel obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
