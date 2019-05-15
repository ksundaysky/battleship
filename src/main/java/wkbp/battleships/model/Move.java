package wkbp.battleships.model;

/**
 * @author Wiktor Rup
 */
public class Move {

    private User user;
    private Field fieldToShoot;

    public Move(User user, Field fieldToShoot) {
        this.user = user;
        this.fieldToShoot = fieldToShoot;
    }

    public Field getFieldToShoot() {
        return fieldToShoot;
    }

    public User getUser() {
        return user;
    }
}
