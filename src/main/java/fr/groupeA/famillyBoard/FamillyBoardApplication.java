package fr.groupeA.famillyBoard;

import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.services.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FamillyBoardApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(FamillyBoardApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception{

		User usertest = new User(1L, "toto", "test", "test@mail.fr", "0123456789", LocalDate.of(1990, 3, 2), "1234", null, null);

		System.out.println("user = " + usertest);

		LocalDate today = LocalDate.now();
		LocalDate birthDateUser = usertest.getBirthDate();
		Period p = Period.between(birthDateUser, today);
		System.out.println("Vous avez " + p.getYears() + " ans");

		if (p.getYears() >= 18 ){
			System.out.println("Creation de famille");

			List<User> e = new ArrayList<>();
			e.add(usertest);

			Family familyTest = new Family(1L, "La famille de TOTO", e);

			List<Family> f = new ArrayList<>();
			f.add(familyTest);

			usertest.setUserFamilies(f);

			System.out.println("La famille " + familyTest + " a été créée");
		} else {
			System.out.println("Vous êtes trop jeune");
		}
	}

	/*voiture v1 = new voiture( peugeot, 307);
	voiture v2 = new voiture( audi, R8);

	personne p1 = new personne ( mickael, jordan); // Liste voiture
	personne p2 = new personne ( lebron, james); // Liste voiture

	List<Voitures> listVoitures;

	listVoiture.add(v1);
	listVoiture.add(v2);

	p1.setVoiture(v1)*/
}
