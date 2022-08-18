package com.hutu.lsz.ssm.mapper;

import com.hutu.lsz.ssm.entity.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role row);

    Role selectByPrimaryKey(String id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role row);
}