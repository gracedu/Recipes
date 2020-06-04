package com.ascending.training.repository;

import com.ascending.training.model.Recipe;
import com.ascending.training.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String DBURL = "jdbc:postgresql://localhost:5431/recipes";
    private static final String USER = "admin";
    private static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());

    //create a user
    public void createUser(User user) {
        Connection conn = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");

            String sql;
            sql = "INSERT INTO users (user_name, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            logger.info("Filling data...");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error("error found", e);
        } finally {
            try {
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    //read all users
    public List<User> getUsers() {
        List<User> users = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);
            logger.info("Converting data...");
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long userId  = rs.getLong("user_id");
                String userName = rs.getString("user_name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                //Fill the object
                User user = new User();
                user.setUserId(userId);
                user.setUserName(userName);
                user.setEmail(email);
                user.setPassword(password);
                users.add(user);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("error found", e);
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return users;
    }

    public String getUserNameByEmail(String email) {
        Connection conn = null;
        String userName = null;
        ResultSet rs = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");

            String sql;
            sql = "SELECT user_name from users where email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            logger.info("Filling data...");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userName = rs.getString("user_name");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error("error found", e);
        } finally {
            try {
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return userName;
    }

    //update password
    public void updatePassword(String email, String newPassword) {
        Connection conn = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");

            String sql;
            sql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            logger.info("Filling data...");
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error("error found", e);
        } finally {
            try {
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    //delete an user account
    public void deleteUser(String email) {
        Connection conn = null;

        try {
            //STEP 2: Open a connection
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //STEP 3: Execute a query
            logger.info("Creating statement...");

            String sql;
            sql = "DELETE FROM users where email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            logger.info("Filling data...");
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            logger.error("error found", e);
        } finally {
            try {
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
