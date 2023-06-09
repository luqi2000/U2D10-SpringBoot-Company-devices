package luqmanmohammad.U2D10SpringBootCompanydevices.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Device {
	@Id
	@GeneratedValue
	private UUID id;
	private DeviceStatus deviceStatus;
	private DeviceType deviceType;
	
	public enum DeviceStatus {
		AVAIlABLE,ASSIGNED,MAINTENANCE,DISCONTINUED
	}
	public enum DeviceType{
		SMARTPHONE,LAPTOP,TABLET
	}
	
	@ManyToOne
	public User user;
}