package io.nurgissa.queueoverflow.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE,
                    Permission.MODERATOR_READ,
                    Permission.MODERATOR_UPDATE,
                    Permission.MODERATOR_CREATE,
                    Permission.MODERATOR_DELETE
            )
    ),
    MODERATOR(
            Set.of(
                    Permission.MODERATOR_READ,
                    Permission.MODERATOR_UPDATE,
                    Permission.MODERATOR_CREATE,
                    Permission.MODERATOR_DELETE
            )
    )
    ;
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
