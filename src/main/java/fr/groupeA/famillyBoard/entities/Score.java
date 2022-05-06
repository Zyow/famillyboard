package fr.groupeA.famillyBoard.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Entity
@Table( name = "score" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    //@Min(message = "Score can't be negative", value = 0)
    private Integer score;

}
