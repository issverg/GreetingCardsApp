package il.co.sela.security;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import il.co.sela.configuration.AccountConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class TokenAuthenticationManager implements AuthenticationManager{
	@Autowired
	AccountConfiguration accConfig;
	@Autowired
	UDServiceImpl udService;
	@Value("${privateKey}")
	String pKey;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
		UserDetails userDetails;
		String token = tokenAuthentication.getToken();
		String login = null;
		
		if (token != null) {
			Claims claims = Jwts.parser()
								.setSigningKey(DatatypeConverter.parseBase64Binary(pKey))
								.parseClaimsJws(token)
								.getBody();
			login = claims.get("email", String.class);
		}
		
		try {
			userDetails = udService.loadUserByUsername(login);
			tokenAuthentication.setUserDetails(userDetails);
			tokenAuthentication.setAuths(userDetails.getAuthorities());
		} catch (Exception e) {}
		
		tokenAuthentication.setAuthenticated(true);
		return tokenAuthentication;
	}
}