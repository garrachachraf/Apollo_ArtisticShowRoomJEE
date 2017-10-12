package tn.esprit.Apollo.services;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import tn.esprit.Apollo.Facade.CrudBone;
import tn.esprit.Apollo.Facade.EntityBone;
import tn.esprit.Apollo.persistence.ArtWork;

public  class AbstractFacade <T extends EntityBone> {
    private final Class<T> entitys;
	public AbstractFacade(Class<T> entity) {
		this.entitys = entity;
	}

	@PersistenceContext
	private EntityManager em;	
	
public T create(final T entity) throws EntityExistsException,
	IllegalStateException, IllegalArgumentException,
	TransactionRequiredException {
     em.persist(entity);
     em.flush();
     return entity;
                }

public T read(final Serializable primaryKey) throws IllegalStateException,
	IllegalArgumentException {
     return   em.find(entitys,primaryKey);
}

public Boolean update(final T entity) throws IllegalStateException,
	IllegalArgumentException, TransactionRequiredException {
em.merge(entity);
em.flush();
return true;
}

public Boolean delete(final T entity) throws IllegalStateException,
	IllegalArgumentException, TransactionRequiredException,
	PersistenceException {
	em.remove(em.contains(entity) ? entity : em.merge(entity));;
em.flush();
return true;
}


}
