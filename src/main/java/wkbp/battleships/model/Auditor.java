package wkbp.battleships.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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

    private Move move;
    private boolean hitTheShip;
    private boolean wonTheGame;

    public Auditor() {
        this.wonTheGame = false;
        this.hitTheShip = false;
    }

    public void update(Move move, boolean hitTheShip, boolean wonTheGame) {
        this.move = move;
        this.hitTheShip = hitTheShip;
        this.wonTheGame = wonTheGame;
        writeMessageToFile();
    }

    // TODO: 14.05.19  - implementacja metody i zapisywania przebiegu gry do bazy danych w optymistycznej wersji sprintu
    void writeMessageToDatabase() {
    }

    public String writeMessageToFront() {
        if (move == null) {
            return "Game hasn't started yet.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(move.getPlayer().getName()).append("shot on a field ").append(move.getFieldToShoot().getId());
            if (hitTheShip) {
                stringBuilder.append("Ship has been shot!");
            } else stringBuilder.append("Missed!");
            if (wonTheGame) {
                stringBuilder.append(move.getPlayer().getName()).append(" won the game!");
            }
            return stringBuilder.toString();
        }
    }

    public String getMessageFromAuditor() {
        return "Player " + move.getPlayer() +
                " fired at field: " + getCoordinatesOfField(move.getFieldToShoot()) +
                "\nresult: hit the ship: " + hitTheShip + ", won: " + wonTheGame;
    }

    private void writeMessageToFile() {

    }

    String getCoordinatesOfField(Field field) {
            char letterA = 'A';
            long id = field.getId();
            char desiredLetter = (char) (letterA + (id / 10));
            String number = String.valueOf((id % 10)+1);
            String letter = String.valueOf(desiredLetter);
            return letter + number;
    }
}
