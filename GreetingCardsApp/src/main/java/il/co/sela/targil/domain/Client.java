package il.co.sela.targil.domain;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"email"})
@Document(collection = "user")
public class Client {
	@Id
	String email;
	Gender gender;
	String firstName;
	String lastName;
	String password;
	@Singular
	Set<String> roles;
	@Singular
	List<Long> myCards;
	@Singular
	List<Long> myTemplates;
}