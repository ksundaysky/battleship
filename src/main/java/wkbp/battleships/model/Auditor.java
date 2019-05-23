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
 * Responsible for transcripting flow of the game.
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

    public Auditor() {
        this.wonTheGame = false;
        this.hitTheShip = false;
    }

    public void update(Move move, boolean wonTheGame, boolean hitTheShip) {
        this.lastMove = move;
        this.hitTheShip = hitTheShip;
        this.wonTheGame = wonTheGame;
        //writeMessageToFile(); todo testy się sypio
    }

    // TODO: 14.05.19  - implementacja metody i zapisywania przebiegu gry do bazy danych w optymistycznej wersji sprintu
    void writeMessageToDatabase() {
    }

    public String writeMessageToFront() {
        if (lastMove == null) {
            return "Game hasn't started yet.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(lastMove.getPlayer().getName()).append("shot on a field ").append(lastMove.getFieldToShoot().getId());
            if (hitTheShip) {
                stringBuilder.append("Ship has been shot!");
            } else stringBuilder.append("Missed!");
            if (wonTheGame) {
                stringBuilder.append(lastMove.getPlayer().getName()).append(" won the game!");
            }
            return stringBuilder.toString();
        }
    }

    public String auditLastMove() {
        return "Player " + lastMove.getPlayer().getName() +
                " fired at field: " + getCoordinatesOfField(lastMove.getFieldToShoot()) +
                "\nResult: hit the ship: " + hitTheShip;
    }

    private void writeMessageToFile() {// TODO: 22.05.19 kozak try-with-resources
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
}
