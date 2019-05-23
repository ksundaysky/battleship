package wkbp.battleships.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import wkbp.battleships.dao.repository.entity.GameEntity;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;

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
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private UserInGameEntity userInGameEntity;
    private String gameName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isWinner;
    private int shots;
    private int hits;
    private int ratio;

    public Summary(String gameName, User user, UserInGameEntity userInGameEntity, boolean isWinner, int shots, int hits, int ratio) {
        this.gameName = gameName;
        this.user = user;
        this.userInGameEntity = userInGameEntity;
        this.isWinner = isWinner;
        this.shots = shots;
        this.hits = hits;
        this.ratio = ratio;
    }


}
