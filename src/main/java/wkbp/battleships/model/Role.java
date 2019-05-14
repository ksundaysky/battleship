package wkbp.battleships.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Represents role of users which determines permissions and
 * authorization for given actions on the client and server side
 *
 * @author Krzysztof Niedzielski
 * @author Bartek Kupajski
 */

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

    public Role() {
    }

    /**
     * @param name of the role {@link RoleName}
     */

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}