package wkbp.battleships.model;

/**
 * Responsible for transcripting flow of the game.
 *
 * @author Wiktor Rup
 */
public class Auditor {

    private Move move;
    private boolean hitTheShip;
    private boolean wonTheGame;

    Auditor() {
        this.wonTheGame = false;
        this.hitTheShip = false;
    }

    void update(Move move, boolean hitTheShip, boolean wonTheGame) {
        this.move = move;
        this.hitTheShip = hitTheShip;
        this.wonTheGame = wonTheGame;
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
}
