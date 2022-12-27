package com.example.exchangeRate.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.exchangeRate.security.ApplicationUserPermission.DAILYRATE_READ;
import static com.example.exchangeRate.security.ApplicationUserPermission.DAILYRATE_WRITE;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(DAILYRATE_READ)),
    ADMIN(Sets.newHashSet(DAILYRATE_WRITE, DAILYRATE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        System.out.println(permissions);
        return permissions;
    }

}
