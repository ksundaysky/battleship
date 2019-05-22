package wkbp.battleships.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import wkbp.battleships.dto.SummaryDTO;

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
    private boolean isWinner;
    private int shots;
    private int hits;
    private float ratio;

    public Summary(String gameName, User user, boolean isWinner, int shots, int hits, float ratio) {
        this.gameName = gameName;
        this.user = user;
        this.isWinner = isWinner;
        this.shots = shots;
        this.hits = hits;
        this.ratio = ratio;
    }


}
