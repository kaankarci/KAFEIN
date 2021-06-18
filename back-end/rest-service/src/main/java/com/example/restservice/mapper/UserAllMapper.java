package com.example.restservice.mapper;

import com.example.restservice.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserAllMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setIsim(resultSet.getString("isim"));
        user.setSoyisim(resultSet.getString("soyisim"));
        user.setKullaniciTipi(resultSet.getString("kullaniciTipi"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));


        return user;
    }


}
