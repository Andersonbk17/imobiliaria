/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IVendaImovelRepositorio;
import br.edu.ifnmg.imobiliaria.domainModel.VendaImovel;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(VendaImovel obj) {
        try {

            Query query = manager.createQuery("Update VendaImovel s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
