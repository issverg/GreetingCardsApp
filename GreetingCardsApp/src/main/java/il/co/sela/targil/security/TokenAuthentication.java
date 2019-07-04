package il.co.sela.targil.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TokenAuthentication implements Authentication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isAuthenticated;
	@Getter @Setter
	private String token;
	@Getter @Setter
	private UserDetails userDetails;
	@Setter
	private Collection<? extends GrantedAuthority> auths;

	@Override
	public String getName() {
		return userDetails.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return auths;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return userDetails;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}
}