package wkbp.battleships.dao.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wkbp.battleships.model.GameState;
import wkbp.battleships.model.Summary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
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
