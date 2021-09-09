/**
 * 
 */
package com.ranasoftcraft.diagnostic.security.services;

import java.util.Optional;

import com.ranasoftcraft.diagnostic.security.entity.Roles;
import com.ranasoftcraft.diagnostic.security.entity.Users;

import javassist.NotFoundException;

/**
 * @author sandeep.rana
 *
 */
public interface UsersService {

	/**
	 * Sign up the users ... 
	 * @param user request payload ... 
	 * @return
	 */
	Users signUp(Users user);

	Optional<Users> findByUserId(String userName);

	Roles getRole(String roleId) throws NotFoundException;

}
