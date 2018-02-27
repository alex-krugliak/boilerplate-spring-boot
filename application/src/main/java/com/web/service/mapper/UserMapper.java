package com.web.service.mapper;

import com.web.service.wrapper.UserWrapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 19.02.18.
 */
public class UserMapper implements RowMapper<UserWrapper> {

    @Override
    public UserWrapper mapRow(ResultSet resultSet, int i) throws SQLException {
        UserWrapper user = new UserWrapper();
        user.setId(resultSet.getLong(1));
        user.setName(resultSet.getString(2));
        user.setEmail(resultSet.getString(3));
        return user;
    }
}
