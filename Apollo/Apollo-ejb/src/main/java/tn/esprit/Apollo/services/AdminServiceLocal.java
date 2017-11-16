package tn.esprit.Apollo.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Apollo.persistence.Admin;
@Local
public interface AdminServiceLocal {

	Admin CreateAdmin(Admin u);

	void UpdateAdmin(Admin u);

	void DeleteAdmin(int id);

	Admin FindAdminById(int id);

	List<Admin> GetAllAdmins();

	boolean loginCheck(String UserName, String Password);

	Admin FindUserByUsername(String username);

}
