package com.ticoyk.sspractice.config;

import java.util.Set;

import com.google.common.collect.Sets;

import static com.ticoyk.sspractice.config.UserRolePermission.COURSE_READ;
import static com.ticoyk.sspractice.config.UserRolePermission.COURSE_WRITE;
import static com.ticoyk.sspractice.config.UserRolePermission.STUDENT_READ;
import static com.ticoyk.sspractice.config.UserRolePermission.STUDENT_WRITE;

public enum UserRoles {
    STUDENT(Sets.newHashSet()), 
    ADMIN(Sets.newHashSet(
        COURSE_READ, 
        COURSE_WRITE, 
        STUDENT_READ, 
        STUDENT_WRITE
    ));

    private final Set<UserRolePermission> permissions;

    UserRoles(Set<UserRolePermission> permissions){
        this.permissions = permissions;
    }

    public Set<UserRolePermission> getPermissions(){
        return permissions;
    }

}
