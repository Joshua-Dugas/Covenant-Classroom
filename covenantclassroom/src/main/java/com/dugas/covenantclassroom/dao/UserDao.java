package com.dugas.covenantclassroom.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate covenantJdbcTemplate;
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, email, password, role) VALUES (:username, :email, :password, :role)";
    private static final String SELECT_USER = "SELECT username, email, password, role FROM users WHERE username = :username";

    @Autowired
    public UserDao(NamedParameterJdbcTemplate covenantJdbcTemplate) {
        this.covenantJdbcTemplate = covenantJdbcTemplate;
    }

    public void createUser(String username, String email, String password, String role) {
        var params = new MapSqlParameterSource();
        params.addValue("username", username);
        params.addValue("email", email);
        params.addValue("password", password);
        params.addValue("role", role);
        covenantJdbcTemplate.update(INSERT_USER_SQL, params);
    }

    public com.dugas.covenantclassroom.model.User selectUser(String username){
        var params = new MapSqlParameterSource().addValue("username", username);
        return covenantJdbcTemplate.queryForObject(SELECT_USER, params, (rs, rowNum) ->
            new com.dugas.covenantclassroom.model.User(
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
            )
        );
    }

}
