package il.co.sela.targil.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import il.co.sela.targil.dao.UsersRepository;
import il.co.sela.targil.domain.Client;
import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.dto.LoginOutputDto;
import il.co.sela.targil.dto.RegUserDto;
import il.co.sela.targil.exceptions.EmailExistsException;
import il.co.sela.targil.exceptions.NotFoundException;
import il.co.sela.targil.exceptions.WrongPasswordException;
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
		validatingRegUserDto(regUserDto.getEmail());
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

	private boolean validatingRegUserDto(String email) { //Meaning that all fields validations are implementing on frontend part
		if (repo.existsById(email)) {					 //such as email and password formats, empty/not empty etc.
			throw new EmailExistsException("User with such email: " + email + " is exists, already!");
		}
		
		return true;
	}

	@Override
	public LoginOutputDto signIn(LoginInputDto loginInputDto) {
		validatingLoginInputDto(loginInputDto.getEmail(), loginInputDto.getPassword());
		String token = tokenService.createToken(loginInputDto);
		return new LoginOutputDto(token);
	}

	private boolean validatingLoginInputDto(String email, String password) {
		if (!repo.existsById(email)) {
			throw new NotFoundException("User with email: " + email + " does not exist!");
		}
		
		if (!BCrypt.checkpw(password, repo.findById(email).get().getPassword())) {
			throw new WrongPasswordException("Wrong password!");
		}
		return true;
	}
}