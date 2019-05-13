package wkbp.battleships.business.logic;

import wkbp.battleships.model.*;

import java.util.*;

/**
 * @author Wiktor Rup
 */
public class ShipsRandomize {

    private static final List<Compass> VALUES =
            Collections.unmodifiableList(Arrays.asList(Compass.values()));
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
            //tu sie dzieje losowanko na Board i wielkosci ship
            if (VALUES.get(RANDOM.nextInt(SIZE)).equals(Compass.HORIZONTAL)) {
                do {
                    field = board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
                }
                while (field.getStateOfField().equals(StateOfField.OCCUPIED) ||
                        field.getStateOfField().equals(StateOfField.ILLEGAL_TO_PLACE) ||
                        board.getColumns() - (ship.getSize()) < field.getId() % board.getColumns() ||
                        checkIfShipCanBePlacedHorizontally(ship.getSize(), field));

                for (int i = 0; i < ship.getSize(); i++) {
                    board.getFieldList().get(field.getId() + i).setStateOfField(StateOfField.OCCUPIED);
                    ships.add(board.getFieldList().get(field.getId() + i));
                }

                for (int j = 0; j < ship.getSize(); j++) {

                    Field currentField = board.getFieldList().get((field.getId()) + j);
                    for (int i = -1; i < 2; i++) {
                        if (indexExists(board.getFieldList(), currentField.getId() - board.getColumns() + i)) {
                            if (!(board.getFieldList().get(currentField.getId() - board.getColumns() + i).getStateOfField().equals(StateOfField.OCCUPIED))) {
                                board.getFieldList().get(currentField.getId() - board.getColumns() + i).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                            }
                        }
                    }
                    for (int i = -1; i < 2; i++) {
                        if (indexExists(board.getFieldList(), currentField.getId() + board.getColumns() + i)) {
                            if (!(board.getFieldList().get(currentField.getId() + board.getColumns() + i).getStateOfField().equals(StateOfField.OCCUPIED))) {
                                board.getFieldList().get(currentField.getId() + board.getColumns() + i).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                            }
                        }
                    }
                    if (indexExists(board.getFieldList(), currentField.getId() + 1)) {
                        if (!(board.getFieldList().get(currentField.getId() + 1).getStateOfField().equals(StateOfField.OCCUPIED))) {
                            board.getFieldList().get(currentField.getId() + 1).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                        }
                    }
                    if (indexExists(board.getFieldList(), currentField.getId() - 1)) {
                        if (!(board.getFieldList().get(currentField.getId() - 1).getStateOfField().equals(StateOfField.OCCUPIED))) {
                            board.getFieldList().get(currentField.getId() - 1).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                        }
                    }
                }


            } else {
                do {
                    field = board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
                }
                while (field.getStateOfField().equals(StateOfField.OCCUPIED) ||
                        field.getStateOfField().equals(StateOfField.ILLEGAL_TO_PLACE) ||
                        board.getColumns() - ship.getSize() < field.getId() / board.getColumns() ||
                        checkIfShipCanBePlacedVertically(ship.getSize(), field));
                for (int i = 0; i < ship.getSize(); i++) {
                    board.getFieldList().get(field.getId() + (i * 10)).setStateOfField(StateOfField.OCCUPIED);
                    ships.add(board.getFieldList().get(field.getId() + (i * 10)));
                }
                for (int j = 0; j < ship.getSize(); j++) {

                    Field currentField = board.getFieldList().get((field.getId()) + (j * 10));
                    for (int i = -1; i < 2; i++) {
                        if (indexExists(board.getFieldList(), currentField.getId() - board.getColumns() + i)) {
                            if (!(board.getFieldList().get(currentField.getId() - board.getColumns() + i).getStateOfField().equals(StateOfField.OCCUPIED))) {
                                board.getFieldList().get(currentField.getId() - board.getColumns() + i).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                            }
                        }
                    }
                    for (int i = -1; i < 2; i++) {
                        if (indexExists(board.getFieldList(), currentField.getId() + board.getColumns() + i)) {
                            if (!(board.getFieldList().get(currentField.getId() + board.getColumns() + i).getStateOfField().equals(StateOfField.OCCUPIED))) {
                                board.getFieldList().get(currentField.getId() + board.getColumns() + i).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                            }
                        }
                    }
                    if (indexExists(board.getFieldList(), currentField.getId() + 1)) {
                        if (!(board.getFieldList().get(currentField.getId() + 1).getStateOfField().equals(StateOfField.OCCUPIED))) {
                            board.getFieldList().get(currentField.getId() + 1).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                        }
                    }
                    if (indexExists(board.getFieldList(), currentField.getId() - 1)) {
                        if (!(board.getFieldList().get(currentField.getId() - 1).getStateOfField().equals(StateOfField.OCCUPIED))) {
                            board.getFieldList().get(currentField.getId() - 1).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                        }
                    }
                }
            }
        }
        return ships;
    }

    private boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
    }

    private boolean checkIfShipCanBePlacedHorizontally(int shipSize, Field startingPosition) {

        for (int i = 0; i < shipSize; i++) {
            if (board.getFieldList().get(startingPosition.getId() + i).getStateOfField().equals(StateOfField.OCCUPIED) ||
                    board.getFieldList().get(startingPosition.getId() + i).getStateOfField().equals(StateOfField.ILLEGAL_TO_PLACE)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfShipCanBePlacedVertically(int shipSize, Field startingPosition) {

        for (int i = 0; i < shipSize; i++) {
            if (board.getFieldList().get(startingPosition.getId() + i * 10).getStateOfField().equals(StateOfField.OCCUPIED) ||
                    board.getFieldList().get(startingPosition.getId() + i * 10).getStateOfField().equals(StateOfField.ILLEGAL_TO_PLACE)) {
                return true;
            }
        }
        return false;
    }

}
