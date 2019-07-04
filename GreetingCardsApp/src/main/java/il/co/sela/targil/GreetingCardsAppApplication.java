package il.co.sela.targil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import il.co.sela.targil.dao.UsersRepository;
import il.co.sela.targil.domain.Client;

@SpringBootApplication
public class GreetingCardsAppApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UsersRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(GreetingCardsAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String pass = encoder.encode("adminadmin");
		if (!repository.existsById("iss@gmail.com")) {
			Client client = Client.builder()
					.email("iss@gmail.com")
					.password(pass)
					.role("ROLE_ADMIN")
					.build();
			repository.save(client);
		}
	}
}