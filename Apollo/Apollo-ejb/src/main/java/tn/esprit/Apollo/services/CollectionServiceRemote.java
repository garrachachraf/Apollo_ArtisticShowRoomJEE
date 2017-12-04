package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Remote;
import tn.esprit.Apollo.Facade.CrudBone;
import tn.esprit.Apollo.persistence.Collection;
@Remote
public interface CollectionServiceRemote  extends CrudBone<Collection>{
	public List<Collection> findByUser(int artistId);
}
