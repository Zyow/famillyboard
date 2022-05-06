package fr.groupeA.famillyBoard.entities;

import fr.groupeA.famillyBoard.enums.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "familyMember" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Family family;

    @Column
    private EnumRole role;

    @OneToOne
    private Score score;

}
