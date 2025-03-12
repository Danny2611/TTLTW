package com.example.finallaptrinhweb.dao;

import com.example.finallaptrinhweb.connection_pool.DBCPDataSource;
import com.example.finallaptrinhweb.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    public List<Role> getAllRole(){
        List<Role> roles = new ArrayList<>();
        try {
            Connection connection = DBCPDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, roleName FROM roles where id <> ?");
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Role role = new Role();
                role.setRoleName(resultSet.getString("roleName"));
                role.setId(resultSet.getInt("id"));
                roles.add(role);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  roles;
    }
}
