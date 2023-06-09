package luqmanmohammad.U2D10SpringBootCompanydevices.entities;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private UUID id;
	private String username;
	private String name;
	private String surname;
	private String email;
	
	public User(String username, String name, String surname, String email) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	@OneToMany(mappedBy = "user")
	private List<Device> devices;
}