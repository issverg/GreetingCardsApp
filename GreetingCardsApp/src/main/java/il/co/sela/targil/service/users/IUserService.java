package il.co.sela.targil.service.users;

import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.dto.LoginOutputDto;
import il.co.sela.targil.dto.RegUserDto;

public interface IUserService {
	
	LoginOutputDto signUp(RegUserDto regUserDto);
	
	LoginOutputDto signIn(LoginInputDto loginInputDto);
}