package com.seaweed.hm.modules.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER","사용자"),
    ADMIN("ROLE_ADMIN","관리자"),
    OPEN_API("ROLE_OPEN_API","OpenAPI");

    private final String key;
    private final String title;
}
