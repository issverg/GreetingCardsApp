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
public class CardFromTemplateInputDto {
	Prefix prefix;
	String firstName;
	String lastName;
	String wishes;
	String signature;
	boolean isPublic;
}