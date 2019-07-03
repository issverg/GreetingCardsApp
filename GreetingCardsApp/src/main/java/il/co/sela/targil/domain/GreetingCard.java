package il.co.sela.targil.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Document(collection = "greeting_cards")
public class GreetingCard {
	@Id
	long id;
	String title;
	String enteringWord;
	enum prefix {
		MR, MRS, MS, DR
	}
	String firstName;
	String lastName;
	String celebration;
	String wishes;
	String endingWord;
	String signature;
	String backgroundImageUrl;
	boolean isTemplate;
	
	public GreetingCard(String title, String enteringWord, 
						String celebration, String endingWord, 
						String backgroundImageUrl) {
		super();
		this.title = title;
		this.enteringWord = enteringWord;
		this.celebration = celebration;
		this.endingWord = endingWord;
		this.backgroundImageUrl = backgroundImageUrl;
		isTemplate = true;
	}
}