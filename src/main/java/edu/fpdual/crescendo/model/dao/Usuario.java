package edu.fpdual.crescendo.model.dao;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;


public class Usuario {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private int id;
    private String nombre;
    private String password;
    private String email;
    private Date fechaNacimiento;

    public Usuario() {

    }

    public Usuario(ResultSet result) {
        try {
            this.setId(result.getInt("id_usuario"));
            this.nombre = result.getString("username");
            this.password = result.getString("password");
            this.email = result.getString("email");
            //this.fechaNacimiento = result.getDate("birth_date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            this.setFechaNacimiento(result.getDate("birth_date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


