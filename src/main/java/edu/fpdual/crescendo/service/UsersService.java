package edu.fpdual.crescendo.service;

import edu.fpdual.crescendo.model.manager.UsersManager;
import edu.fpdual.crescendo.model.manager.impl.UsersManagerImpl;
import edu.fpdual.crescendo.model.connector.MySQLConnector;
import edu.fpdual.crescendo.model.dao.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersService {

    public final UsersManager usersManager;

    //Constructor
    public UsersService(UsersManagerImpl usersManager) {
        this.usersManager = usersManager;
    }

    public Usuario findByNameAndPass(String username, String pwd) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.findByNameAndPass(con, username, pwd);
        }
    }

    public boolean create(Usuario usuario) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.create(con, usuario);
        }
    }

    public boolean updatePassword(String name, String email, String pwd) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.updatePassword(con, name, email, pwd);
        }
    }

    public boolean delete(String user, String pwd) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return usersManager.delete(con, user, pwd);
        }
    }
}
