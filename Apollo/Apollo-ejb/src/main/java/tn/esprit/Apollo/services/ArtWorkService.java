package tn.esprit.Apollo.services;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import tn.esprit.Apollo.Facade.CrudBone;
import tn.esprit.Apollo.Facade.EntityBone;
import tn.esprit.Apollo.persistence.ArtWork;
@Stateless
@LocalBean
public class ArtWorkService extends AbstractFacade<ArtWork> implements ArtWorkServiceRemote{

	public ArtWorkService() {
		super(ArtWork.class);
		// TODO Auto-generated constructor stub
	}
}
