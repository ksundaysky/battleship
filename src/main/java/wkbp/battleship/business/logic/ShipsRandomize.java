package wkbp.battleship.business.logic;

import wkbp.battleship.model.*;

import java.util.*;

/**
 * Class which is responsible for randomizing ships {@link wkbp.battleship.model.Ship} on the initial board {@link wkbp.battleship.model.Board}
 * @author Wiktor Rup
 */
public class ShipsRandomize {
    private static final List<Compass> VALUES =
            List.of(Compass.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private Board board;
    private Fleet fleet;


    public ShipsRandomize() {
    }

    public ShipsRandomize(Board board, Fleet fleet) {
        this.fleet = fleet;
        this.board = board;
    }


    public List<Field> randomizeShips() {
        List<Field> ships = new ArrayList<>();
        Field field;
        for (Ship ship : fleet.getShipList()) {
            if (VALUES.get(RANDOM.nextInt(SIZE)).equals(Compass.HORIZONTAL)) {
                do {
                    field = board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
                }
                while (field.getStateOfField().equals(StateOfField.OCCUPIED) &&
                        board.getSizeOfBoard() - (ship.getSize()) < field.getId() % board.getSizeOfBoard());
                for (int i = 0; i < ship.getSize(); i++) {
                    board.getFieldList().get(field.getId() + i).setStateOfField(StateOfField.OCCUPIED);
                    ships.add(board.getFieldList().get(field.getId() + i));
                }
            } else {
                do {
                    field = board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
                }
                while (field.getStateOfField().equals(StateOfField.OCCUPIED) &&
                        board.getSizeOfBoard() - (ship.getSize()) < field.getId() / board.getSizeOfBoard());
                for (int i = 0; i < ship.getSize(); i += board.getSizeOfBoard()) {
                    board.getFieldList().get(field.getId() + i).setStateOfField(StateOfField.OCCUPIED);
                    ships.add(board.getFieldList().get(field.getId() + i * 10));
                }
            }
        }
        return ships;
    }
}
