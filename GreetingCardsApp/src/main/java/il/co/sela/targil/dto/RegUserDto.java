package il.co.sela.targil.dto;

import il.co.sela.targil.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegUserDto {
	String email;
	Gender gender;
	String firstName;
	String lastName;
	String password;
}