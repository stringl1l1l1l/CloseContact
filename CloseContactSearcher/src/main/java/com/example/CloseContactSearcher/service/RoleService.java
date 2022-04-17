package com.example.CloseContactSearcher.service;

import com.example.CloseContactSearcher.entity.Role;
import com.example.CloseContactSearcher.entity.UserRole;

import java.util.List;

public interface RoleService {

    List<Role> showAllRoles();

    List<Role> findAllRoles();

    List<Role> findAllRolesOfOneUser(Long userId);

    int insertUserRole(UserRole userRole);

    int deleteUserRole(UserRole userRole);

    int updateRoleById(Role role);

    int deleteRoleById(Integer roleId);

    int insertRole(Role role);

    int restoreRoleById(Integer roleId);
}
