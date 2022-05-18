package edu.fpdual.crescendo.model.manager.impl;

import edu.fpdual.crescendo.model.dao.Usuario;
import edu.fpdual.crescendo.model.manager.UsersManager;

import java.sql.*;

public class UsersManagerImpl implements UsersManager {

    @Override
    public Usuario findByNameAndPass (Connection con, String nombre, String pwd){
        //prepare SQL statement
        String sql = "select * "
                + "from users "
                + "where username = ? "
                + "and password = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1,nombre);
            stmt.setString(2, pwd);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            Usuario usuario = null;

            // Run through each result
            while (result.next()) {
                // Initializes a usuario per result
                usuario = new Usuario(result);
            }

            return usuario;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(Connection con, Usuario usuario) {
        //prepare SQL statement
        String sql = "INSERT INTO users (username, password, email, birth_date)" +
                     " values(?,?,?,?)";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.setDate(4, usuario.getFechaNacimiento());


            // Queries the DB
            int affectedRows = stmt.executeUpdate();
            if(affectedRows<=0){
                return false;
            }

            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public boolean updatePassword(Connection con, String name, String email, String pwd) {
        //prepare SQL statement
        String sql = "UPDATE users " +
                "SET password = ?" +
                "WHERE username = ? AND email = ?;";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setString(1, pwd);
            stmt.setString(2, name);
            stmt.setString(3, email);

            // Queries the DB
            int affectedRows = stmt.executeUpdate();
            if(affectedRows<=0){
                return false;
            }

            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.beforeFirst();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public boolean delete(Connection con, String user, String pwd) {
        //prepare SQL statement
        String sql = "DELETE FROM users " +
                    "WHERE username = ? AND password = ?";
        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, user);
            stmt.setString(2,pwd);
            // Queries the DB
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
