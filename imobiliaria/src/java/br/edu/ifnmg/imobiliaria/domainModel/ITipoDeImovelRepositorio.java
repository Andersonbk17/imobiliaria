/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.imobiliaria.domainModel;

import javax.ejb.Remote;

/**
 *
 * @author Anderson
 */
@Remote
public interface ITipoDeImovelRepositorio extends IRepositorio<TipoDeImovel>{

    public boolean verificaESalva(TipoDeImovel obj) throws Exception;
    
}
