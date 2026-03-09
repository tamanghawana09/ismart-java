package com.example.crudapplication.Service;


import com.example.crudapplication.Entities.Users;
import com.example.crudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}";

    public static boolean valEmail(String email, String emailRegex){
        Pattern emailPat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    //save user information
    public Users saveUser(Users user){
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        return userRepository.save(user);
    }

    //fetch all users
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    //fetch user by id
    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }

    //update user
    public Users updateUser(Long id, Users updatedUser){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());

                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }



                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("user not found with id " + id));
    }


    //delete user

    public void deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new RuntimeException("user not found with id" + id);
        }
    }
    public Optional<Users> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
