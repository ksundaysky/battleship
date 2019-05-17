package wkbp.battleships.dao.repository.entity;

import wkbp.battleships.model.User;

import javax.persistence.*;

/**
 * @author Wiktor Rup
 */
@Entity
@Table(name = "USER_IN_GAME")
public
class UserInGameEntity {

    UserInGameEntity() {
    }

    public UserInGameEntity(User user, GameEntity gameEntity) {
        this.gameEntity = gameEntity;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameentity_id", nullable = false)
    private GameEntity gameEntity;
}
