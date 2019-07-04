package il.co.sela.targil.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import il.co.sela.targil.dao.UsersRepository;
import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.dto.LoginOutputDto;
import il.co.sela.targil.dto.RegUserDto;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UsersRepository repo;

	@Override
	public LoginOutputDto signUp(RegUserDto regUserDto) {
		// TODO signUp
		return null;
	}

	@Override
	public LoginOutputDto signIn(LoginInputDto loginInputDto) {
		// TODO signIn
		return null;
	}
}