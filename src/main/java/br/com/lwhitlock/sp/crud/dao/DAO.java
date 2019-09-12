/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lwhitlock.sp.crud.dao;

import br.com.lwhitlock.sp.crud.abstracts.AbstractDAO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ra167363
 */
public class DAO extends AbstractDAO {

    @Override
    @PersistenceContext(unitName = "default-pu")
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
