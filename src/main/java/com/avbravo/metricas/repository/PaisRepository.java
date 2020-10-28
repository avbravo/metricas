/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.metricas.repository;

import com.avbravo.jmoordb.mongodb.repository.Repository;
import com.avbravo.metricas.entity.Pais;
import javax.ejb.Stateless;

/**
 *
 * @author avbravo
 */
@Stateless
public class PaisRepository extends Repository<Pais>{

    public PaisRepository() {
        super(Pais.class, "jconfperu","pais");
    }
    
}
