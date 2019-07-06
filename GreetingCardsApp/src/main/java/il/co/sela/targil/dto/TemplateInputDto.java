package il.co.sela.targil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateInputDto {
	String title;
	String enteringWord;
	String celebration;
	String endingWord;
	String backgroundImageUrl;
	boolean isPublic;
	boolean isTemplate;
}