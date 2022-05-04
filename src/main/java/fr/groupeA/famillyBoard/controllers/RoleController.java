package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.Role;
import fr.groupeA.famillyBoard.services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( path = "/api/roles" )
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getRoles(){ return roleService.getAllRoles(); }

    @GetMapping( path = {"roleId"} )
    public Optional<Role> getRoleById(@PathVariable Long roleId){ return roleService.getRoleById(roleId);}

    @PostMapping()
    public Role addRole(@RequestBody Role role){ return  roleService.createRole(role);}

    @PutMapping(path = "{roleId}")
    public Role updateRole(@PathVariable Long roleId, @RequestBody Role role){
        return roleService.updateRole(roleId, role);
    }

    @DeleteMapping(path = "{roleId}")
    public void deleteRole(@PathVariable Long roleId){ roleService.deleteRoleById(roleId);}
}
