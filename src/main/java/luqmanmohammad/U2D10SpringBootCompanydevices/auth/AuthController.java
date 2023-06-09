package luqmanmohammad.U2D10SpringBootCompanydevices.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.U2D10SpringBootCompanydevices.auth.payload.AuthenticationSuccessfullPayload;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.User;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.UserPayload;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.payload.UserLoginPayload;
import luqmanmohammad.U2D10SpringBootCompanydevices.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserPayload body) {
		User createdUser = userService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body) {
		User aldo = new User("aldobaglio","aldo", "baglio" , "aldo_2000@gmail.com", "783492790588");
		String token = JWTTools.createToken(aldo);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}