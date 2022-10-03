package web.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DefaultUser {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DefaultUser(UserService userRepository, RoleService roleRepository) {
        this.userService = userRepository;
        this.roleService = roleRepository;
    }

    @PostConstruct
    public void initialize() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.save(user);
        roleService.save(admin);

        User defaultAdmin = new User("admin@mail.com", "admin");
        User defaultUser = new User("user@mail.com","user");

        defaultAdmin.setRole(roleService.findByName("ROLE_ADMIN"));
        defaultAdmin.setRole(roleService.findByName("ROLE_USER"));
        defaultUser.setRole(roleService.findByName("ROLE_USER"));

        defaultAdmin.setName("Admin");
        defaultAdmin.setSurname("Adminov");

        defaultUser.setName("User");
        defaultUser.setSurname("Userov");

        userService.saveUser(defaultAdmin);
        userService.saveUser(defaultUser);


    }
}
