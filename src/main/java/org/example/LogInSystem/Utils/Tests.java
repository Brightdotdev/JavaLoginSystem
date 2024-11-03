package org.example.LogInSystem.Utils;

import org.example.LogInSystem.DAO.UserDao;
import org.example.LogInSystem.Models.UserModel;

import java.sql.SQLException;

public class Tests{
    public static void main(String[] args) throws SQLException {
        UserModel userModel = new UserModel("David", "email@mail.com","davidIsaGoat");

        UserDao userData = new UserDao();

        boolean isUser = userData.userVerification(userModel.getEmail()  ,userModel.getPassword()+ " see something");

        System.out.println(isUser);


    }
}
