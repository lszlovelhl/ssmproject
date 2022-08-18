package com.hutu.lsz.ssm.mapper;

import com.hutu.lsz.ssm.entity.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission row);

    Permission selectByPrimaryKey(String id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission row);
}