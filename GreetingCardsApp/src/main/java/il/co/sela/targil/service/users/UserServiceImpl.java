package il.co.sela.targil.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import il.co.sela.targil.dao.UsersRepository;
import il.co.sela.targil.domain.Client;
import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.dto.LoginOutputDto;
import il.co.sela.targil.dto.RegUserDto;
import il.co.sela.targil.security.TokenService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UsersRepository repo;
	@Autowired
	TokenService tokenService;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public LoginOutputDto signUp(RegUserDto regUserDto) {
		Client client = Client.builder()
				.email(regUserDto.getEmail().toLowerCase())
				.gender(regUserDto.getGender())
				.firstName(regUserDto.getFirstName())
				.lastName(regUserDto.getLastName())
				.password(encoder.encode(regUserDto.getPassword()))
				.role("ROLE_USER").build();
		repo.save(client);
		
		return signIn(new LoginInputDto(regUserDto.getEmail(), regUserDto.getPassword()));
	}

	@Override
	public LoginOutputDto signIn(LoginInputDto loginInputDto) {
		String token = tokenService.createToken(loginInputDto);
		return new LoginOutputDto(token);
	}
}