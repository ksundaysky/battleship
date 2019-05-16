package wkbp.battleships.dao.repository.entity;

import wkbp.battleships.model.GameState;
import wkbp.battleships.model.User;

import javax.persistence.*;

/**
 * @author krzysztof.niedzielski
 */

@Entity
public class GameEntity {

    public GameEntity(User owner, GameState gameState) {
        this.owner = owner;
        this.gameState = gameState;
    }

    public GameEntity(User owner, User opponent, GameState gameState) {
        this.owner = owner;
        this.opponent = opponent;
        this.gameState = gameState;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "OWNER")
    private User owner;

    @Column(name = "OPPONENT")
    private User opponent;

    @Column(name = "GAME_STATE")
    private GameState gameState;


    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public User getOpponent() {
        return opponent;
    }

    public GameState getGameState() {
        return gameState;
    }
}
