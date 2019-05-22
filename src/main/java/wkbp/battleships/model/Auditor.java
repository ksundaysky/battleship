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

    private Move lastMove;
    private boolean hitTheShip;
    private boolean wonTheGame;

    public Auditor() {
        this.wonTheGame = false;
        this.hitTheShip = false;
    }

    public void update(Move move, boolean hitTheShip, boolean wonTheGame) {
        this.lastMove = move;
        this.hitTheShip = hitTheShip;
        this.wonTheGame = wonTheGame;
        writeMessageToFile();
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
                "\nresult: hit the ship: " + hitTheShip;
    }

    private void writeMessageToFile() {

    }

    String getCoordinatesOfField(Field field) {
        char letterA = 'A';
        long id = field.getId();
        char desiredLetter = (char) (letterA + (id / 10));
        String number = String.valueOf((id % 10) + 1);
        String letter = String.valueOf(desiredLetter);
        return letter + number;
    }
}
