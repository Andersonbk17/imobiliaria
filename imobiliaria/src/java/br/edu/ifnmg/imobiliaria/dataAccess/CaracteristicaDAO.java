/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.dataAccess;

import br.edu.ifnmg.imobiliaria.domainModel.Caracteristica;
import br.edu.ifnmg.imobiliaria.domainModel.ICaracteristica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Anderson
 */
@Stateless(name = "ICaracteristica")
public class CaracteristicaDAO extends DAOGenerico<Caracteristica> implements ICaracteristica{

    public CaracteristicaDAO() {
        super(Caracteristica.class);
    }

    @Override
    public List<Caracteristica> Buscar(Caracteristica obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(Caracteristica obj) {
         try {

            Query query = manager.createQuery("Update Caracteristica s set s.ativo = 0 WHERE s.id :=id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
