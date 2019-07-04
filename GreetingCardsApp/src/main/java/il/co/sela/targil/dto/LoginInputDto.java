package il.co.sela.targil.dto;

import il.co.sela.targil.annotations.IsValidLoginInputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IsValidLoginInputDto
public class LoginInputDto {
	String email;
	String password;
}