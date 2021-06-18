package com.example.restservice.repository;

import com.example.restservice.mapper.UserAllMapper;
import com.example.restservice.mapper.UserInfoMapper;
import com.example.restservice.model.User;
import com.example.restservice.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserInfo> getUser() {
        return jdbcTemplate.query("SELECT id,isim,soyisim,kullaniciTipi from usertbl", new UserInfoMapper());
    }


    public UserInfo findById(Long id) {
        String sql = "Select id,isim,soyisim,kullaniciTipi from usertbl WHERE ID = ?";
        try {
            return (UserInfo) this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserInfoMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
    public User findByIdAll(Long id) {
        String sql = "Select id,isim,soyisim,kullaniciTipi,username,password from usertbl WHERE ID = ?";
        try {
            return (User) this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserAllMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public Boolean saveUser(User user) {
        String sql = "insert into usertbl values(?,?,?,?,?,?)";
        return jdbcTemplate.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setLong(1, user.getId());
                ps.setString(2, user.getIsim());
                ps.setString(3, user.getSoyisim());
                ps.setString(4, user.getKullaniciTipi());
                ps.setString(5, user.getUsername());
                ps.setString(6, user.getPassword());

                return ps.execute();
            }
        });
    }

    public Integer updateUser(User user) {
        String sql = "update usertbl set isim=?, soyisim=?, kullaniciTipi=?, username=?, password=?";
        Object[] params = {
                user.getIsim(),
                user.getSoyisim(),
                user.getKullaniciTipi(),
                user.getUsername(),
                user.getPassword()
        };
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
        return  jdbcTemplate.update(sql,params,types);
    }

    public Integer deleteUserById(Integer id){
        return  jdbcTemplate.update("delete from usertbl where id=?",id);
    }

}

