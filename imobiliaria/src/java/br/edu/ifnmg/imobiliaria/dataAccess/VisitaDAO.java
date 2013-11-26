/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.IVisita;
import br.edu.ifnmg.imobiliaria.domainModel.Visita;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "IVisita")
public class VisitaDAO extends DAOGenerico<Visita> implements IVisita{

    public VisitaDAO() {
        super(Visita.class);
    }

    @Override
    public List<Visita> Buscar(Visita obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(Visita obj) {
        try {

            Query query = manager.createQuery("Update Visita s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
