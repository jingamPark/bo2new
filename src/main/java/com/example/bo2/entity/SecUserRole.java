package com.example.bo2.entity;


import lombok.Getter;

@Getter
public enum SecUserRole {
    //관리자와 일반사용자의 상수를 만들고, 해당상수에 값을 부여
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    SecUserRole(String value) {
        this.value = value;
    }

}
