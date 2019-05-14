package wkbp.battleships.businesslogic;

import wkbp.battleships.model.*;

import java.util.*;

/**
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * <p>
 * Class responsible for random ship placement on board,
 *
 */

public class ShipsRandomiser {

    private static final List<Compass> VALUES =
            Collections.unmodifiableList(Arrays.asList(Compass.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private Board board;
    private Fleet fleet;

    private boolean isHorizontal = false;
    private int multiplier = 1;

    /**
     * @param board for ships to be placed on
     * @param fleet ships to be placed
     */

    public ShipsRandomiser(Board board, Fleet fleet) {
        this.fleet = fleet;
        this.board = board;
    }

    /**
     * Main method that draws random direction (HORIZONTAL, VERTICAL)
     * and puts ship on board depending on drawing output.
     *
     * {@link Compass}
     *
     * @return List of fields with ships, which is to be sent to client
     */

    public List<Field> randomizeShips() {
        List<Field> randomizedShips = new ArrayList<>();
        for (Ship ship : fleet.getShipList()) {
            if (VALUES.get(RANDOM.nextInt(SIZE)).equals(Compass.HORIZONTAL)) {
                isHorizontal = true;
                multiplier = 1;
            } else {
                isHorizontal = false;
                multiplier = 10;
            }
            putShipOnBoard(randomizedShips, ship);
        }
        return randomizedShips;
    }

    /**
     * @param randomizedShips list of fields to be sent to client
     * @param ship            ship to be placed on board
     */

    private void putShipOnBoard(List<Field> randomizedShips, Ship ship) {
        Field startingPosition = getValidFieldForShip(ship);
        for (int i = 0; i < ship.getSize(); i++) {
            board.getFieldList().get(startingPosition.getId() + (i * multiplier)).setStateOfField(StateOfField.OCCUPIED);
            randomizedShips.add(board.getFieldList().get(startingPosition.getId() + (i * multiplier)));
        }
        setNeighbourFieldsOfShipAsIllegal(startingPosition, ship);
    }

    /**
     * draws a field from the board until it's fully valid for a ship to
     * be placed on
     *
     * @param ship to be placed on board
     * @return valid starting field for @param ship
     * <p>
     * {@link #checkIfFieldIsOccupied(Field startingPosition)}
     * {@link #checkIfFieldIsIllegal(Field startingPosition)}
     * {@link #checkIfShipCanFit(Field startingPosition, Ship ship)}
     * {@link #ifShipHasRoomToBePlaced(Ship ship, Field startingPosition)}
     */

    private Field getValidFieldForShip(Ship ship) {
        Field startingPosition;
        do {
            startingPosition = getRandomFieldFromBoard();
        }
        while (checkIfFieldIsOccupied(startingPosition) ||
                checkIfFieldIsIllegal(startingPosition) ||
                checkIfShipCanFit(startingPosition, ship) ||
                ifShipHasRoomToBePlaced(ship, startingPosition));

        return startingPosition;
    }

    private boolean checkIfShipCanFit(Field startingPosition, Ship ship) {
        if (isHorizontal) {
            return board.getDimension() - (ship.getSize()) < startingPosition.getId() % board.getDimension();
        }
        return board.getDimension() - ship.getSize() < startingPosition.getId() / board.getDimension();
    }

    private boolean checkIfFieldIsIllegal(Field startingPosition) {
        return startingPosition.getStateOfField().equals(StateOfField.ILLEGAL_TO_PLACE);
    }

    private boolean checkIfFieldIsOccupied(Field startingPosition) {
        return startingPosition.getStateOfField().equals(StateOfField.OCCUPIED);
    }

    private boolean ifShipHasRoomToBePlaced(Ship ship, Field startingPosition) {

        for (int i = 0; i < ship.getSize(); i++) {
            if (checkIfFieldIsOccupied(board.getFieldList().get(startingPosition.getId() + i * multiplier)) ||
                    checkIfFieldIsIllegal(board.getFieldList().get(startingPosition.getId() + i * multiplier))) {
                return true;
            }
        }
        return false;
    }

    private Field getRandomFieldFromBoard() {
        return board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
    }

    /**
     * When ship is placed, set all of neighbour fields as ILLEGAL_TO_PLACE
     * <p>
     * s is for ship, where upper case "S" stands for current field in algorithm,
     * x is for ILLEGAL_TO_PLACE
     * <p>
     * |   |   |   |   |
     * -----------------
     * |   | S | s |   |
     * -----------------
     * |   |   |   |   |
     * -----------------
     * |   |   |   |   |
     * <p>
     * algorithm starts with 3 fields above first field of ship starting from left or top,
     * depending if ship was placed horizontally or vertically
     * <p>
     * | x | x | x |   |
     * -----------------
     * |   | S | s |   |
     * -----------------
     * |   |   |   |   |
     * -----------------
     * |   |   |   |   |
     * <p>
     * then 3 fields below:
     * <p>
     * | x | x | x |   |
     * -----------------
     * |   | S | s |   |
     * -----------------
     * | x | x | x |   |
     * -----------------
     * |   |   |   |   |
     * <p>
     * then ones on the left and right of field (if not OCCUPIED)
     * {@link #changeStateOfFieldToIllegalSingleIndexBeforeOrAfter(Field currentField, int indexDifference)}
     * <p>
     * | x | x | x |   |
     * -----------------
     * | x | S | s |   |
     * -----------------
     * | x | x | x |   |
     * -----------------
     * |   |   |   |   |
     * <p>
     * then algorithm moves on to the next field of the ship
     * and repeats steps until it's done with the whole ship
     * <p>
     * | x | x | x | x |
     * -----------------
     * | x | s | S |   |
     * -----------------
     * | x | x | x |   |
     * -----------------
     * |   |   |   |   |
     * <p>
     * <p>
     * | x | x | x | x |
     * -----------------
     * | x | s | S |   |
     * -----------------
     * | x | x | x | x |
     * -----------------
     * |   |   |   |   |
     * <p>
     * <p>
     * | x | x | x | x |
     * -----------------
     * | x | s | S | x |
     * -----------------
     * | x | x | x | x |
     * -----------------
     * |   |   |   |   |
     */

    private void setNeighbourFieldsOfShipAsIllegal(Field startingPosition, Ship ship) {
        for (int j = 0; j < ship.getSize(); j++) {
            Field currentField = board.getFieldList().get((startingPosition.getId()) + j * multiplier);
            setNeighboursOfFieldAsIllegal(currentField, true);
            setNeighboursOfFieldAsIllegal(currentField, false);
        }
    }

    private void setNeighboursOfFieldAsIllegal(Field currentField, boolean areFieldsAboveCurrentField) {
        int boardDimension = board.getDimension();
        int indexDifference = 1;
        if (areFieldsAboveCurrentField) {
            boardDimension *= -1;
            indexDifference = -1;
        }
        for (int i = -1; i < 2; i++) {
            changeStateOfFieldToIllegal(currentField, boardDimension, i);
        }
        changeStateOfFieldToIllegalSingleIndexBeforeOrAfter(currentField, indexDifference);
    }

    private void changeStateOfFieldToIllegalSingleIndexBeforeOrAfter(Field currentField, int indexDifference) {
        if (indexExists(board.getFieldList(), currentField.getId() + indexDifference)) {
            if (!(checkIfFieldIsOccupied(board.getFieldList().get(currentField.getId() + indexDifference)))) {
                board.getFieldList().get(currentField.getId() + indexDifference).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
            }
        }
    }

    private void changeStateOfFieldToIllegal(Field currentField, int columns, int i) {
        if (indexExists(board.getFieldList(), currentField.getId() + columns + i)) {
            if (!(checkIfFieldIsOccupied(board.getFieldList().get(currentField.getId() + columns + i)))) {
                board.getFieldList().get(currentField.getId() + columns + i).setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
            }
        }
    }

    private boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
    }
}
