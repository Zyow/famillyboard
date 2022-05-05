package fr.groupeA.famillyBoard.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

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
    @DecimalMin(message = "Score can't be negative", value = "0", inclusive = false)
    private Integer score;

}
