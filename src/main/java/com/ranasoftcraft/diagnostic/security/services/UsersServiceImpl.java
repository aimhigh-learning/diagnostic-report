package com.ranasoftcraft.diagnostic.security.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ranasoftcraft.diagnostic.security.entity.Roles;
import com.ranasoftcraft.diagnostic.security.entity.Users;
import com.ranasoftcraft.diagnostic.security.repository.RolesRepository;
import com.ranasoftcraft.diagnostic.security.repository.UsersRepository;

import javassist.NotFoundException;

/**
 * @author sandeep.rana
 *
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService, UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public Users signUp(final Users user) {
		// transform the password ...
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedAt(Instant.now().toEpochMilli());
		user.setUpdatedAt(Instant.now().toEpochMilli());
		usersRepository.save(user);
		return user;
	}
	
	@Override
	public Optional<Users> findByUserId(final String userName) {
		return usersRepository.findById(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Users user = findByUserId(username).orElseThrow(()->new UsernameNotFoundException("User Name :: " + username + " is not found !"));
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Roles> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Roles role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRoleId()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, authorities);
	}
	
	@Override
	public Roles getRole(final String roleId) throws NotFoundException {
		return rolesRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role " + roleId + " not found"));
	}
}
