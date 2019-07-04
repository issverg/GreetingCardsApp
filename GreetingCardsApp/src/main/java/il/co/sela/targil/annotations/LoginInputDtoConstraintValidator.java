package il.co.sela.targil.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import il.co.sela.targil.dao.UsersRepository;
import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.exceptions.NotFoundException;
import il.co.sela.targil.exceptions.WrongPasswordException;

@Service
public class LoginInputDtoConstraintValidator implements ConstraintValidator<IsValidLoginInputDto, LoginInputDto>{
	@Autowired
	UsersRepository repo;

	@Override
	public boolean isValid(LoginInputDto loginInputDto, ConstraintValidatorContext context) {
		if (!repo.existsById(loginInputDto.getEmail())) {
			throw new NotFoundException("User with email: " + loginInputDto.getEmail() + " does not exist!");
		}
		
		if (!BCrypt.checkpw(loginInputDto.getPassword(), repo.findById(loginInputDto.getEmail()).get().getPassword())) {
			throw new WrongPasswordException("Wrong password!");
		}
		
		return true;
	}
}