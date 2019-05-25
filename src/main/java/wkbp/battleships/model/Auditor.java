package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import wkbp.battleships.dao.repository.GameRepository;
import wkbp.battleships.security.jwt.JwtAuthEntryPoint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsible for creating transcriptions of the gameplay
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@EqualsAndHashCode
@Getter
@Setter
class Auditor {

    @Autowired
    private GameRepository gameRepository;
    private Move lastMove;
    private boolean hitTheShip;
    private boolean wonTheGame;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    Auditor() {
        this.wonTheGame = false;
        this.hitTheShip = false;
    }

    public void update(Move move, boolean wonTheGame, boolean hitTheShip) {
        this.lastMove = move;
        this.hitTheShip = hitTheShip;
        this.wonTheGame = wonTheGame;
    }

    /**
     * Creates message corresponding to most recent
     * move made by one of the players
     *
     * @return message with details of last move
     */
    public String auditLastMove() {
        return "Player " + lastMove.getPlayer().getName() +
                " fired at field: " + getCoordinatesOfField(lastMove.getFieldToShoot()) +
                ". Result: hit the ship: " + hitTheShip;
    }

    /**
     * Gets corresponding field coordinates
     *
     * @param field which coordinates are to be generated
     * @return coordinates of the field for example A5
     */
    String getCoordinatesOfField(Field field) {
        char letterA = 'A';
        long id = field.getId();
        char desiredLetter = (char) (letterA + (id / 10));
        String number = String.valueOf((id % 10) + 1);
        String letter = String.valueOf(desiredLetter);
        return letter + number;
    }

    private String currentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void writeMessageToDatabase() {//will be used in future
    }

    private void writeMessageToFile() {//will be used in future
        String textToAppend = auditLastMove();
        try (FileWriter fileWriter = new FileWriter(createNewFile(), true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(textToAppend);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private String createNewFile() {
        String fileName = "./game_transcripts/" + currentDate() + "_" + gameRepository.getOne(lastMove.getGameId()).getId() + "_";
        new File(fileName);
        return fileName;
    }
}
