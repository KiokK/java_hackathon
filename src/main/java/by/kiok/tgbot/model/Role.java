package by.kiok.tgbot.model;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public enum Role {

    ADMIN(List.of(
            AuthorityRights.ADMIN.getGrantedAuthority(),
            AuthorityRights.READER.getGrantedAuthority()
    )),
    USER(List.of(
            AuthorityRights.READER.getGrantedAuthority()
    ));

    private final List<GrantedAuthority> authorities;

    Role(List<GrantedAuthority> grantedAuthorities) {
        this.authorities = grantedAuthorities;
    }

}
