package wkbp.battleships.dao.repository.entity;

import lombok.NoArgsConstructor;
import wkbp.battleships.model.User;

import javax.persistence.*;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@NoArgsConstructor
@Entity
@Table(name = "USER_IN_GAME")
public
class UserInGameEntity {

    public UserInGameEntity(User user, GameEntity gameEntity) {
        this.gameEntity = gameEntity;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gameentity_id", nullable = false)
    private GameEntity gameEntity;
}
