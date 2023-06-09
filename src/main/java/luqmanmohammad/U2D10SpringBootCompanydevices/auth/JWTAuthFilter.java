package luqmanmohammad.U2D10SpringBootCompanydevices.auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import luqmanmohammad.U2D10SpringBootCompanydevices.repository.UserRepository;
import luqmanmohammad.U2D10SpringBootCompanydevices.service.UserService;


//logica per estrarre il token dal header il token verificarlo e poi dire procedi o no
@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 0. Questo metodo verrà invocato per ogni request
		// 1. Prima di tutto dovrò estrarre il token dall'Authorization Header
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer "))
			throw new BadRequestException("you have to add the token");

		String accessToken = authHeader.substring(7);

		// 2. Verifico che il token non sia stato nè manipolato nè sia scaduto
		JWTTools.isTokenValid(accessToken);

		// 3. Se OK

		// 3.0 Estraggo l'email dal token e cerco l'utente
		String email = JWTTools.extractSubject(accessToken);
		System.out.println(email);
		try {
			Optional<User> utente = userRepo.findByEmail(email);

			// 3.1 Aggiungo l'utente al SecurityContextHolder

//			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(utente, null,
//					utente.getAuthorities());
//			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//			SecurityContextHolder.getContext().setAuthentication(authToken);

			// 3.2 puoi procedere al prossimo blocco della filterChain
			filterChain.doFilter(request, response);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}

}