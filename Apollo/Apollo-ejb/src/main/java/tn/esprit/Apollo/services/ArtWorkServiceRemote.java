package tn.esprit.Apollo.services;

import javax.ejb.Remote;

import tn.esprit.Apollo.Facade.CrudBone;
import tn.esprit.Apollo.persistence.ArtWork;

@Remote
public interface ArtWorkServiceRemote extends CrudBone<ArtWork>{

  
}
