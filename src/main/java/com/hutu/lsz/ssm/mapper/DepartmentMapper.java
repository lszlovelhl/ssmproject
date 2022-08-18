package com.hutu.lsz.ssm.mapper;

import com.hutu.lsz.ssm.entity.Department;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Department row);

    Department selectByPrimaryKey(String id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department row);
}