package edu.fpdual.crescendo.model.manager;

import edu.fpdual.crescendo.model.dao.Usuario;

import java.sql.Connection;

public interface UsersManager {

    Usuario findByNameAndPass (Connection con, String name, String pwd);
    boolean create (Connection con, Usuario usuario);
    boolean updatePassword (Connection con, String name, String email, String pwd);
    boolean delete(Connection con, String name, String pwd);
}