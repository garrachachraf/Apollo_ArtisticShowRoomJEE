package tn.esprit.Apollo.services;

import javax.ejb.Remote;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

import tn.esprit.Apollo.Facade.CrudBone;
import tn.esprit.Apollo.persistence.Collection;
import tn.esprit.Apollo.persistence.User;
@Remote
public interface AuthentificationServiceRemote  extends CrudBone<User>{
	

	
}
