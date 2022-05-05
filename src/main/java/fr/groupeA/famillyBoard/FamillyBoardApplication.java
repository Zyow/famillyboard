package fr.groupeA.famillyBoard;

import fr.groupeA.famillyBoard.entities.*;
import fr.groupeA.famillyBoard.enums.EnumRole;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		// Création d'un utilisateur
		User user1 = new User(
				1L,
				"toto",
				"test",
				"test@mail.fr",
				"0123456789",
				LocalDate.of(1990, 3, 2),
				"1234",
				null
				);

		System.out.println("user = " + user1);

		// Vérifier l'âge de l'utilisateur
		LocalDate today = LocalDate.now();
		LocalDate birthDateUser = user1.getBirthDate();
		Period p = Period.between(birthDateUser, today);
		System.out.println("Vous avez " + p.getYears() + " ans");

		if (p.getYears() >= 18 ) {
			System.out.println("Creation de famille");

			// Création d'une famille
			Family family1 = new Family(1L, "Family One");
			System.out.println("Creation d'une famille : " + family1);

			// Création d'un score
			Score score1 = new Score(1L, 0);
			System.out.println("Creation d'un score : " + score1);

			// Création d'un membre de la famille

			FamilyMember familyMember1 = new FamilyMember(
					1L,
					user1,
					family1,
					EnumRole.ADMINISTRATOR,
					score1,
					null);
			System.out.println("Creation d'un membre d'une famille : " + familyMember1);


			//Créer une liste de tâches
			Task task1 = new Task(
					1L,
					"task1",
					"acheter du pain",
					true,
					LocalDate.of(2022, 05,05),
					LocalDate.of(2022, 05,05),
					2,
					familyMember1);

			List<Task> tasks = new ArrayList<>();
			tasks.add(task1);
			//familyMember1.setTaskList(tasks);
			System.out.println("afficher la tâche : " + familyMember1);

		}
	}
}
