package com.example.ComplaintSystem.service.user;

import com.example.ComplaintSystem.dto.UsersDto;
import com.example.ComplaintSystem.model.Users;

public interface IUserService {
    Users getUserById(int userId);
    void RegisterUser(Users newUser);
    Users updateUser(UsersDto userDto, int userId);
    void deleteUser(int userId);
    String verify(UsersDto userDto);
}
