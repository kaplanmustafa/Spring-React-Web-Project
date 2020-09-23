package com.hoaxify.ws.user;

import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.shared.CurrentUser;
import com.hoaxify.ws.shared.GenericResponse;
import com.hoaxify.ws.user.vm.UserVM;

@RestController
@RequestMapping("/api/1.0")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users")// @Valid ile Hibernate kısıtlamaları kontrol eder
	public GenericResponse createUser(@Valid @RequestBody User user) { // gelen json objesi User'a map edilir
		userService.save(user);
		return new GenericResponse("User Created"); // response da JSON'a çevrilir
	}
	
	@GetMapping("/users")
	Page<UserVM> getUsers(Pageable page, @CurrentUser User user) { // map --> User'ı UserVM'e çevirir
		return userService.getUsers(page, user).map(new Function<User, UserVM>() {

			public UserVM apply(User user) {
				return new UserVM(user);
			}
		});
	}
	
	@GetMapping("/users/{username}") 
	UserVM getUser(@PathVariable String username) {
		User user = userService.getByUsername(username);
		return new UserVM(user);
	}
}
