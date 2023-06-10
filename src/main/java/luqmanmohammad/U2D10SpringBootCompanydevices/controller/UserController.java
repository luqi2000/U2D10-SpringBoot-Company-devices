package luqmanmohammad.U2D10SpringBootCompanydevices.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.U2D10SpringBootCompanydevices.entities.User;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.UserPayload;
import luqmanmohammad.U2D10SpringBootCompanydevices.exceptions.NotFoundException;
import luqmanmohammad.U2D10SpringBootCompanydevices.service.UserService;

@RestController
@RequestMapping("/employee")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//working
	@GetMapping("")
	public List<User> getAllUser() {
		return userService.findAll();
	}
	
	//working
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated User body){
		return userService.create(body);
	}
	
	//just for try 
//	{
//	    "name" : "Luqman",
//	    "surname" : "Mohammad",
//	    "email" : "luqmanmohammad09@gmail.com",
//	    "password": "12345678910",
//	    "username" : "luqi"
//	}
	
	//working
	@GetMapping("/{userId}")
	public User getUser(@PathVariable UUID userId) throws NotFoundException {
		return userService.findById(userId);
	}
	
	//working
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable UUID userId, @RequestBody User body) throws Exception {
		return userService.findByIdAndUpdate(userId, body);
	}
	
	//working
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId){
		userService.findByIdAndDelete(userId);
	}

}
