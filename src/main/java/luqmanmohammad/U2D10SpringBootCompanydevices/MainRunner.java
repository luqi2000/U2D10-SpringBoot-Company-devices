package luqmanmohammad.U2D10SpringBootCompanydevices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import luqmanmohammad.U2D10SpringBootCompanydevices.entities.Device;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.Device.DeviceStatus;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.Device.DeviceType;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.User;
import luqmanmohammad.U2D10SpringBootCompanydevices.service.UserService;

@Component
public class MainRunner implements CommandLineRunner{
	
	@Autowired
	UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
	
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 50; i++) {
			try {
				String username = faker.name().username();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password  = faker.code().asin();
				User user = new User(username, name, surname, email, password);
				//userService.create(user);
			
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
