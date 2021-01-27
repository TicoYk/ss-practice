package com.ticoyk.sspractice.config;

public enum UserRolePermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    UserRolePermission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return this.permission;
    }


}
