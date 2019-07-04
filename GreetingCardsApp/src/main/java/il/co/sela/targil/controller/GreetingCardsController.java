package il.co.sela.targil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.dto.LoginOutputDto;
import il.co.sela.targil.dto.RegUserDto;
import il.co.sela.targil.service.users.IUserService;

@RestController
@RequestMapping("/greetingCard")
public class GreetingCardsController {
	@Autowired
	IUserService service;
	
	@PostMapping("/signup")
	public LoginOutputDto signUp(@RequestBody RegUserDto regUserDto) {
		return service.signUp(regUserDto);
	}
	
	@PostMapping("/signin")
	public LoginOutputDto signIn(@RequestBody LoginInputDto loginInputDto) {
		return service.signIn(loginInputDto);
	}
}