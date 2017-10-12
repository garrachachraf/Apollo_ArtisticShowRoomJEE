package tn.esprit.Apollo.services;

import javax.ejb.Remote;
import tn.esprit.Apollo.Facade.CrudBone;
import tn.esprit.Apollo.persistence.Collection;
@Remote
public interface CollectionServiceRemote  extends CrudBone<Collection>{

}
