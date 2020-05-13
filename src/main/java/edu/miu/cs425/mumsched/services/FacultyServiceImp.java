package edu.miu.cs425.mumsched.services;

import edu.miu.cs425.mumsched.dao.RoleRepository;
import edu.miu.cs425.mumsched.dao.UserRepository;
import edu.miu.cs425.mumsched.domain.Faculty;
import edu.miu.cs425.mumsched.domain.Role;
import edu.miu.cs425.mumsched.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Demisew Mokonnen, Dereje Enkossa, Tsegaye Beza, Bekalu Assegid
 * @2020
 */
//@Service
//@Qualifier("faculty")
public class FacultyServiceImp implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
    public FacultyServiceImp(UserRepository userRepository,
                             RoleRepository roleRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Faculty findUserByEmail(String email) {
        return (Faculty) userRepository.findByEmail(email);
    }

    public Faculty findUserByUserName(String userName) {
        return (Faculty) userRepository.findByUserName(userName);
    }

    public Faculty saveUser(User user, String role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return (Faculty) userRepository.save(user);
    }

}
