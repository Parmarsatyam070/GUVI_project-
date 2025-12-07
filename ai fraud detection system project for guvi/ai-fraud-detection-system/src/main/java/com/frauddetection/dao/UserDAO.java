/*package com.frauddetection.dao;

import com.frauddetection.model.User;
import com.frauddetection.exception.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO implements UserDAOOperations {

    // Save a User to MySQL
    @Override
    public boolean saveUser(User u) {
        String sql = "INSERT INTO users (user_id, username, email, password_hash, registration_date) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getUserId());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPasswordHash());
            int result = ps.executeUpdate();
            System.out.println("User Insert result: " + result);
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve Users from MySQL
    @Override
    public List<User> getAllUsers() throws DatabaseException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, email, password_hash FROM users";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getString("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPasswordHash(rs.getString("password_hash"));
                users.add(u);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error fetching users", e);
        }
        return users;
    }
}*/


package com.frauddetection.dao;

import com.frauddetection.model.User;

import java.sql.*;

public class UserDAO extends BaseDAO {

    public int register(User u) {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword()); // TODO hash later
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);  // return user ID
                }
            }
            return -1;
        } catch (SQLIntegrityConstraintViolationException dup) {
            return -1; // duplicate email
        } catch (SQLException e) {
            throw new RuntimeException("Error registering user", e);
        }
    }

    public User login(String email, String password) {
        String sql = "SELECT id, name, email, password FROM users WHERE email = ? AND password = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Login failed", e);
        }
    }

    public User findByEmail(String email) {
        String sql = "SELECT id, name, email, password FROM users WHERE email = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading user", e);
        }
    }
}
