package il.co.sela.targil.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import il.co.sela.targil.dao.UsersRepository;

@Service
public class UDServiceImpl implements UserDetailsService {
	@Autowired
	UsersRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		il.co.sela.targil.domain.Client client = repo.findById(email).orElseThrow(() 
								-> new UsernameNotFoundException("User with email: " 
												+ email + " is not found!"));
		return new User(email, client.getPassword(), AuthorityUtils.createAuthorityList(client.getRoles().toArray(new String [] {})));
	}
}