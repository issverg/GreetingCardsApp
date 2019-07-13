package il.co.sela.targil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import il.co.sela.targil.dto.LoginInputDto;
import il.co.sela.targil.dto.LoginOutputDto;
import il.co.sela.targil.dto.RegUserDto;
import il.co.sela.targil.service.users.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(description = "REST API Controller for authorization methods.")
@RestController
public class AuthController {
	@Autowired
	IUserService userService;
	
	@ApiOperation("Registration. Public free access method.")
	@PostMapping("/signup")
	public LoginOutputDto signUp(
			@ApiParam("Data transfer object for registration.")
			@RequestBody RegUserDto regUserDto) {
		return userService.signUp(regUserDto);
	}
	
	@ApiOperation("Logging in. Public free access method.")
	@PostMapping("/signin")
	public LoginOutputDto signIn(
			@ApiParam("Data transfer object for logging in.")
			@RequestBody LoginInputDto loginInputDto) {
		return userService.signIn(loginInputDto);
	}
}