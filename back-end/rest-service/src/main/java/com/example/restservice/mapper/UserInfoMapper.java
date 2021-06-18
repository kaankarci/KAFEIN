package com.example.restservice.mapper;


import com.example.restservice.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoMapper implements RowMapper {

    public Object mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(resultSet.getLong("id"));
        userInfo.setIsim(resultSet.getString("isim"));
        userInfo.setSoyisim(resultSet.getString("soyisim"));
        userInfo.setKullaniciTipi(resultSet.getString("kullaniciTipi"));


        return userInfo;
    }

}

