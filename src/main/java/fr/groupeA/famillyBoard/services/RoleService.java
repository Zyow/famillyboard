package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.Role;
import fr.groupeA.famillyBoard.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //Récupération de tout les rôles
    public List<Role> getAllRoles(){ return  roleRepository.findAll(); }

    //Récupération d'un role par ID
    public Optional<Role> getRoleById(Long id){ return roleRepository.findById(id); }

    //Création d'un role
    public Role createRole(Role role){ return  roleRepository.save(role); }

    //Mise à jour d'un role
    public Role updateRole(Long id, Role role){

        Role roleTemp = roleRepository.findById(id).get();

        roleTemp.setRole(role.getRole());

        return  roleRepository.save(roleTemp);
    }

    //Suppression d'un role
    public void deleteRoleById(Long id){ roleRepository.deleteById(id); }
}
