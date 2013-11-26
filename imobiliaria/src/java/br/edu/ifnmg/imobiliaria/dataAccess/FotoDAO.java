/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.Foto;
import br.edu.ifnmg.imobiliaria.domainModel.IFotoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IFotoRepositorio")
public class FotoDAO extends DAOGenerico<Foto> implements IFotoRepositorio{

    public FotoDAO() {
        super(Foto.class);
    }

    @Override
    public List<Foto> Buscar(Foto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(Foto obj) {
        try {

            Query query = manager.createQuery("Update Foto s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
