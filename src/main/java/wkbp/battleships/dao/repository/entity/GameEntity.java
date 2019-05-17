package wkbp.battleships.dao.repository.entity;

import wkbp.battleships.model.GameState;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author krzysztof.niedzielski
 */

@Entity
@Table(name = "GAME_ENTITY")
public class GameEntity {

    public GameEntity() {
    }

    public GameEntity(GameState gameState) {
        this.gameState = gameState;
    }

//    public GameEntity(User owner, GameState gameState) {
//        this.owner = owner;
//        this.gameState = gameState;
//    }
//
//    public GameEntity(User owner, User opponent, GameState gameState) {
//        this.owner = owner;
//        this.opponent = opponent;
//        this.gameState = gameState;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//
//    @Column(name = "OWNER")
//    @OneToOne
//    @MapsId
//    private User owner;
//
//    @Column(name = "OPPONENT")
//    @OneToOne
//    @MapsId
//    private User opponent;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "gameEntity")
    private Set<UserInGameEntity> userInGameEntities = new HashSet<>();

    @Column(name = "GAME_STATE")
    private GameState gameState;


    public Long getId() {
        return id;
    }

//    public User getOwner() {
//        return owner;
//    }
//
//    public User getOpponent() {
//        return opponent;
//    }

    public GameState getGameState() {
        return gameState;
    }
}
