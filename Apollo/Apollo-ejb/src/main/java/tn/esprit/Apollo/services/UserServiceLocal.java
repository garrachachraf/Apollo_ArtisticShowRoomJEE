package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.core.HttpHeaders;

import tn.esprit.Apollo.persistence.User;



@Local
public interface UserServiceLocal {
	User CreateUser(User u);
	void UpdateUser(User u);
	void DeleteUser(int id);
	User FindUserById(int id);
    User FindUserByUsername(String username);
	List<User> GetAllUsers();
	boolean loginCheck(String UserName , String Password);
}
