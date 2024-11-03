package org.example.LogInSystem.DAOInterfaces;

import org.example.LogInSystem.Models.UserModel;

import java.util.List;

public interface UserDaoInterface {

    boolean addUser(UserModel user);
    UserModel viewUser(String email);
    List<UserModel> viewUsers();
    void deleteUser(int id);

}
