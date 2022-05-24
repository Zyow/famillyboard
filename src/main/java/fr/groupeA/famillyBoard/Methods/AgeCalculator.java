package fr.groupeA.famillyBoard.Methods;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

//    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
//        if ((birthDate != null) && (currentDate != null)) {
//            return Period.between(birthDate, currentDate).getYears();
//        } else {
//            return 0;
//        }
//    }

    public static Period calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate);
        } else {
            return null;
        }
    }

    public static int getYears(LocalDate birthDate){
        LocalDate today = LocalDate.now();
        int years = calculateAge(birthDate, today).getYears();
        return years;
    }
}
