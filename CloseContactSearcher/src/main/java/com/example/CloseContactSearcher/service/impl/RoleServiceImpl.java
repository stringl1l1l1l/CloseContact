package com.example.CloseContactSearcher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.CloseContactSearcher.entity.Role;
import com.example.CloseContactSearcher.entity.UserRole;
import com.example.CloseContactSearcher.mapper.RoleMapper;
import com.example.CloseContactSearcher.mapper.UserRoleMapper;
import com.example.CloseContactSearcher.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> showAllRoles() {
        return roleMapper.showAllRoles();
    }

    @Override
    public List<Role> findAllRoles() {
        return roleMapper.selectList(null);
    }

    @Override
    public List<Role> findAllRolesOfOneUser(Long userId) {
        return roleMapper.findAllRolesOfOneUser(userId);
    }

    @Override
    public int insertUserRole(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    @Override
    public int updateRoleById(Role role) {
        return roleMapper.updateById(role);
    }

    @Override
    public int deleteRoleById(Integer roleId) {
        return roleMapper.deleteById(roleId);
    }

    @Override
    public int deleteUserRole(UserRole userRole) {
        return userRoleMapper.delete(
                new LambdaQueryWrapper<UserRole>()
                        .eq(UserRole::getRoleId, userRole.getRoleId())
                        .eq(UserRole::getUserId, userRole.getUserId())
        );
    }

    @Override
    public int insertRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int restoreRoleById(Integer roleId) {
        return roleMapper.restoreRoleById(roleId);
    }
}
