package wkbp.battleships.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wkbp.battleships.model.Summary;
import wkbp.battleships.model.User;

import javax.persistence.*;

/**
 * @author Wiktor Rup
 */

@Getter
@Setter
@NoArgsConstructor
public class SummaryDTO {

    private String gameName;
    private String user;
    private boolean isWinner;
    private int shots;
    private int hits;
    private float ratio;

    public SummaryDTO(String gameName, String user, boolean isWinner, int shots, int hits, float ratio) {
        this.gameName = gameName;
        this.user = user;
        this.isWinner = isWinner;
        this.shots = shots;
        this.hits = hits;
        this.ratio = ratio;
    }

}
