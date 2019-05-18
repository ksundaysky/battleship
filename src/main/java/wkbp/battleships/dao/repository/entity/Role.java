package wkbp.battleships.dao.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import wkbp.battleships.model.RoleName;

import javax.persistence.*;

/**
 * Represents role of users which determines permissions and
 * authorization for given actions on the client and server side
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    /**
     * @param name of the role {@link RoleName}
     */
    public Role(RoleName name) {
        this.name = name;
    }
}