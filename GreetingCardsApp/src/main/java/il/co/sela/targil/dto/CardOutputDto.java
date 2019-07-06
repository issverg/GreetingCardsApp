package il.co.sela.targil.dto;

import il.co.sela.targil.domain.Prefix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardOutputDto {
	String id;
	String title;
	String enteringWord;
	Prefix prefix;
	String firstName;
	String lastName;
	String celebration;
	String wishes;
	String endingWord;
	String signature;
	String backgroundImageUrl;
}