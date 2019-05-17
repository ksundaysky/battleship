package wkbp.battleships.model;

/**
 * @author Wiktor Rup
 */
public class Move {

    private final long gameId;
    private final User user;
    private final Field fieldToShoot;

    public Move(long gameId, User user, Field fieldToShoot) {
        this.gameId = gameId;
        this.user = user;
        this.fieldToShoot = fieldToShoot;
    }

    public Field getFieldToShoot() {
        return fieldToShoot;
    }

    public User getUser() {
        return user;
    }

    public long getGameId() {
        return gameId;
    }
}
