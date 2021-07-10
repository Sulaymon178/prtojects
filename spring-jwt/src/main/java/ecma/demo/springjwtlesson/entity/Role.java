package ecma.demo.springjwtlesson.entity;

import ecma.demo.springjwtlesson.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    private String description;

    @Override
    public String getAuthority() {
        return this.name.name();
    }

}
