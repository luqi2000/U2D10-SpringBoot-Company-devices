package luqmanmohammad.U2D10SpringBootCompanydevices.service;


import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.User;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.UserPayload;
import luqmanmohammad.U2D10SpringBootCompanydevices.exceptions.BadRequestException;
import luqmanmohammad.U2D10SpringBootCompanydevices.exceptions.NotFoundException;
import luqmanmohammad.U2D10SpringBootCompanydevices.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	// 1. create user
	public User create(UserPayload u) {
		userRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException("email not valid");
		});
		User a = new User(u.getName(), u.getSurname(), u.getEmail(), u.getUsername(), u.getPassword());
		return userRepo.save(a);
	}
	
	// 2. search all users
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	//3 search by id
	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException("employee not found!"));
	}
	
	// 4. find by id and update
	public User findByIdAndUpdate(UUID id, User u) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setName(u.getName());
		found.setSurname(u.getSurname());
		found.setEmail(u.getEmail());
		found.setUsername(u.getUsername());

		return userRepo.save(found);
	}
	//5. find by id and delete
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		userRepo.delete(found);
	}
}
