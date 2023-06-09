package luqmanmohammad.U2D10SpringBootCompanydevices.auth;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.User;

@Component
public class JWTTools {

	private static String secret;
	private static int expiration;

	@Value("${JWT_SECRET}")
	public void setSecret(String secretKey) {
		secret = secretKey;
	}

	@Value("${JWT_EXPIRATION}")
	public void setExpiration(String expirationInDays) {
		expiration = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000;
	}
	//create token
	static public String createToken(User u) {
		String token = Jwts.builder()
				.setSubject(u.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		return token;
	}

	//check if the token is valid
	static public boolean isTokenValid(String token) {
		return true;
	}

	public static String extractSubject(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}
}