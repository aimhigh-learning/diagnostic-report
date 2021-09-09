/**
 * 
 */
package com.ranasoftcraft.diagnostic.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ranasoftcraft.diagnostic.security.entity.Roles;
import com.ranasoftcraft.diagnostic.security.entity.Users;
import com.ranasoftcraft.diagnostic.security.services.UsersService;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sandeep.rana
 *
 */
@Controller
@Slf4j
public class AuthenticationController {

	@Autowired
	private UsersService usersService;
	
	
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");
		return "authentication/login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		return "authentication/signup";
	}
	
	
	@GetMapping({"/","/welcome"})
	public String welcome(Model model) {
		return "dashboard/welcome";
	}
	
	
	@PostMapping(value = "/signup")
	@ResponseBody
	public Users signUp(@RequestBody Users user, @RequestParam String role) throws NotFoundException {
		final Roles roleModel = usersService.getRole(role);
		Set<Roles> roles = new HashSet<>(); roles.add(roleModel);
		user.setRoles(roles);
		log.info("Going to sign up with username "+ user.getUsername());
		return usersService.signUp(user);		
	}
	
	
	
}
