package by.kiok.tgbot.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum AuthorityRights {

    ADMIN(new SimpleGrantedAuthority("admin")),
    READER(new SimpleGrantedAuthority("read")),
    MODIFIER(new SimpleGrantedAuthority("modify"));

    private final GrantedAuthority grantedAuthority;

    AuthorityRights(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }
}
