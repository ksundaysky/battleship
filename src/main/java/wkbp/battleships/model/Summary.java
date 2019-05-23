package wkbp.battleships.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import wkbp.battleships.dao.repository.entity.GameEntity;

import javax.persistence.*;

/**
 * @author Wiktor Rup
 */
@Data
@Entity
@NoArgsConstructor
public class Summary {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String gameName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_entity_id", referencedColumnName = "id")
    private GameEntity gameEntity;
    private boolean isWinner;
    private int shots;
    private int hits;
    private int ratio;

    public Summary(String gameName, User user, GameEntity gameEntity, boolean isWinner, int shots, int hits, int ratio) {
        this.gameName = gameName;
        this.user = user;
        this.gameEntity = gameEntity;
        this.isWinner = isWinner;
        this.shots = shots;
        this.hits = hits;
        this.ratio = ratio;
    }


}
