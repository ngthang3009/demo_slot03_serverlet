/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Utils.util;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    public UserDTO checklogin(String userId, String password) {
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        UserDTO user = null;
        String userID;
        String fullName;
        String pass;
        String roleID;
        try {
            conn = util.getConnection();
            p = conn.prepareStatement("select * from tblUsers where userId = ? and password = ?");
            p.setString(1, userId);
            p.setString(2, password);
            rs = p.executeQuery();

            if (rs.next()) {
                userID = rs.getString("UserID");
                fullName = rs.getString("fullName");
                pass = rs.getString("password");
                roleID = rs.getString("roleID");
                user = new UserDTO(userID, fullName, pass, roleID);
            } else {
                System.out.println("NOT FOUND");
            }
        } catch (SQLException e) {
            System.out.println("Error connection");
        } catch (ClassNotFoundException e) {
            System.out.println("Error connection");
        }
        return user;
    }
}
