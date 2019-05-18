package wkbp.battleships.dao.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wkbp.battleships.model.GameState;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author krzysztof.niedzielski
 */

@NoArgsConstructor
@Getter
@Entity
@Table(name = "GAME_ENTITY")
public class GameEntity {

    public GameEntity(GameState gameState) {
        this.gameState = gameState;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "gameEntity")
    private Set<UserInGameEntity> userInGameEntities = new HashSet<>();

    @Column(name = "GAME_STATE")
    private GameState gameState;
}
