package luqmanmohammad.U2D10SpringBootCompanydevices.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserPayload {		
	@NotNull(message = "username not valid")
	String username;
	@NotNull(message = "name is required")
	String name;
	@NotNull(message = "surname is required")
	String surname;
	@NotNull(message = "email is required")
	@Email(message = "email not valid")
	String email;
	@NotNull(message = "password required")
	@Size(min = 10, max = 30, message = "password not valid. Min 10 characters and max 30 characters")
	String password;
}