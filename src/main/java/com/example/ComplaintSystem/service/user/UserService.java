package com.example.ComplaintSystem.service.user;

import com.example.ComplaintSystem.dto.UsersDto;
import com.example.ComplaintSystem.model.Users;
import com.example.ComplaintSystem.repository.UserRepo;
import com.example.ComplaintSystem.security.jwt.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Override
    public  Users getUserById(int userId) {
        return userRepo.findById(userId).orElseThrow();
    }

    public void RegisterUser(Users newUser){
        Users user=new Users();
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setUserName(newUser.getUserName());
        userRepo.save(user);
    }

    @Override
    public Users updateUser(UsersDto userDto, int userId) {
        Users user=userRepo.findById(userId).orElseThrow();
        if(userDto.getFirstName()!=null){
            user.setFirstName(userDto.getFirstName());
        }
        if(userDto.getLastName()!=null){
            user.setLastName(userDto.getLastName());
        }
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public String verify(UsersDto userDto) {
        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(userDto.getEmail());
        return "fail";
    }
}
