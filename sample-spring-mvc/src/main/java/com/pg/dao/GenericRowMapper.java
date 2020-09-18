package com.pg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class GenericRowMapper implements RowMapper<Map> {

    public Map mapRow(ResultSet rs, int rowNum) throws SQLException {

		Map resultMap = new HashMap();
		resultMap.put("user_id", rs.getString("user_id"));
		resultMap.put("email_address", rs.getString("email_address"));

        return resultMap;

    }
}
