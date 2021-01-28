package com.ticoyk.sspractice.config;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
    )),
    ADMINTRAINEE(Sets.newHashSet(
        COURSE_READ,  
        STUDENT_READ 
    ));

    private final Set<UserRolePermission> permissions;

    UserRoles(Set<UserRolePermission> permissions){
        this.permissions = permissions;
    }

    public Set<UserRolePermission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
