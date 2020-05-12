package edu.miu.cs425.mumsched.services;


import edu.miu.cs425.mumsched.domain.User;

public interface UserService {
    User findUserByEmail(String email) ;
    User findUserByUserName(String userName) ;
    User saveUser(User user, String role) ;
}
