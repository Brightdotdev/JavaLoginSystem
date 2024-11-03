package org.example.LogInSystem.DAO;

import org.example.LogInSystem.DAOInterfaces.UserDaoInterface;
import org.example.LogInSystem.Models.UserModel;
import org.example.LogInSystem.Utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoInterface{
    private final String dbName = "inventoryDb";

      private Connection getConnection() throws SQLException {
     final String url = "jdbc:mysql://localhost:3306/" + this.dbName;

    //your mysql password might be different
     final String password = "admin";
     final String user = "root";
     return DriverManager.getConnection(url,user,password);
    };

    public UserDao() throws SQLException {
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","admin");
        ){
            PreparedStatement dBStatement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + this.dbName);
            dBStatement.execute();
            System.out.println("Database " + dbName + " created successfully");
            String createTable = "CREATE TABLE IF NOT EXISTS "+ this.dbName +".USERS (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(225) NOT NULL," +
                    "password VARCHAR(225) NOT NULL," +
                    "email VARCHAR(225) NOT NULL UNIQUE KEY)";
            PreparedStatement createTableStatement = connection.prepareStatement(createTable);
            createTableStatement.execute();
            System.out.println("Table created successfully");

        }catch (SQLException e){
            throw new RuntimeException("Failed to create database" + dbName,e);
        }
    }

    @Override
    public boolean addUser(UserModel user) {
        String sql = "INSERT INTO USERS (name,password,email) values(?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1,user.getUserName());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getEmail());
           int rowsAffected = statement.executeUpdate();

           if(rowsAffected >0){
               System.out.println("Data inserted successfully");
               return true;
           }else {
               return false;
           }

        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("One email per person");
            Utils.showErrorDialog("One email per person");
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            throw  new RuntimeException("Error", e);
        }
    }

    @Override
    public UserModel viewUser(String email) {
       UserModel user = null;
       String query = "SELECT * FROM USERS WHERE email = ?";
       try (Connection connection = getConnection();
       PreparedStatement queryStatement = connection.prepareStatement(query);
       ) {
           queryStatement.setString(1, email);
           ResultSet results  =  queryStatement.executeQuery();
           if(results.next()){
           user = new UserModel(results.getString("name"),
                   results.getString("email"),
                   results.getString("password"));
       }
       }catch (SQLException e){
           e.printStackTrace();
        Utils.showErrorDialog("User not found");
       }
       return user;
    }

    @Override
    public List<UserModel> viewUsers() {
      List<UserModel> userModels = new ArrayList<>();
      String sqlQuery = "SELECT * FROM USERS";
      try(Connection connectTODb =  getConnection();
      PreparedStatement queryStatement  = connectTODb.prepareStatement(sqlQuery);
      ResultSet resultSet = queryStatement.executeQuery();
      ){
while (resultSet.next()){
    UserModel user  = new UserModel(resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("password"));
    userModels.add(user);
    System.out.println(user.getUserName());
}
        }catch (SQLException e){
          e.printStackTrace();
          throw new RuntimeException("Error getting them all", e);
      }
    return userModels;
    }

    @Override
    public void deleteUser(int id) {
    }

    private boolean validateEmail(String email) throws SQLException {
        ResultSet resultSet;
        String sqlQuery = "SELECT * FROM " + dbName + ".USERS WHERE EMAIL = ?";
        try (
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, email);
        resultSet  = statement.executeQuery();
        return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Omooooooo",e);
        }
    }

    private boolean validatePassword(String password) throws SQLException {
        ResultSet resultSet;
        String sqlQuery = "SELECT * FROM " + dbName + ".USERS WHERE password = ?";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, password);
            resultSet  = statement.executeQuery();
        return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Omooooooo",e);
        }
    }
    public boolean userVerification(String email, String password) throws SQLException {
        boolean isEmail = validateEmail(email);
        boolean isPassword = validatePassword(password);
        if(isEmail && isPassword){
        System.out.println("User validated");
        return true;
    }
     else if(isEmail && !isPassword){
            Utils.showErrorDialog("Wrong password");
        System.out.println("Wrong password");
        return false;
    }
     else if(!isEmail && isPassword){
        Utils.showErrorDialog("Wrong email");
        return false;
    }
        Utils.showErrorDialog("Wrong credentials");
        return false;
    }

    public void bombo(){
        System.out.println("bombo");
    }
}
