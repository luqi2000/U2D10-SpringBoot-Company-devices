package luqmanmohammad.U2D10SpringBootCompanydevices.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import luqmanmohammad.U2D10SpringBootCompanydevices.entities.User;
import luqmanmohammad.U2D10SpringBootCompanydevices.exceptions.BadRequestException;
import luqmanmohammad.U2D10SpringBootCompanydevices.exceptions.NotFoundException;
import luqmanmohammad.U2D10SpringBootCompanydevices.exceptions.UnauthorizedException;
import luqmanmohammad.U2D10SpringBootCompanydevices.repository.UserRepository;
import luqmanmohammad.U2D10SpringBootCompanydevices.service.UserService;

//OncePerRequestFilter has inside a method doFilterInternal that has all the logic for extract from the header 
//the token and verify and then say if you can proceed or not

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 0. This method will be called for every request
		// 1. First I need to extract the token from the Authorization Header
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new UnauthorizedException("Per favore aggiungi il token all'authorization header");

		String accessToken = authHeader.substring(7);

		// 2. I verify that the token has neither been manipulated or has expired
		JWTTools.isTokenValid(accessToken);

		// 3. If OK

		// 3.0 I extract the email from the token and search for the user
		String email = JWTTools.extractSubject(accessToken);
		System.out.println("** " + email);
		try {
			User user = userService.findByEmail(email);

		// 3.1 Aggiungo l'utente al SecurityContextHolder

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authToken);

		// 3.2 puoi procedere al prossimo blocco della filterChain
		filterChain.doFilter(request, response);
	} catch (NotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		// 4. Se non OK -> 401 ("Per favore effettua di nuovo il login")
	}

	// Per evitare che il filtro venga eseguito per OGNI richiesta

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}

}