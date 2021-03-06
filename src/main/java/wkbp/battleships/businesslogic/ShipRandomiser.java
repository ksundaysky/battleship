package wkbp.battleships.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import wkbp.battleships.controller.CantPlaceShipsException;
import wkbp.battleships.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class responsible for random ship placement on board.
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */

public class ShipRandomiser {

    private int counterOfAttempts = 0;
    private final int SIZE = VALUES.size();
    private Board board;
    private Fleet fleet;
    private boolean isHorizontal = false;
    private int multiplier = 1;
    private static final List<Compass> VALUES =
            Collections.unmodifiableList(Arrays.asList(Compass.values()));

    /**
     * @param board for ships to be placed on
     */
    @Autowired
    public ShipRandomiser(Board board) {
        this.fleet = board.getFleet();
        this.board = board;
    }

    /**
     * Main method that draws random direction (HORIZONTAL, VERTICAL)
     * and puts ship on board depending on drawing output.
     *
     * @return List of fields with ships, which is to be sent to client
     * @see Compass
     */
    public List<Field> randomizeShips() throws CantPlaceShipsException {
        List<Field> randomizedShips = new ArrayList<>();
        for (Ship ship : fleet.getShipList()) {
            if (VALUES.get(ThreadLocalRandom.current().nextInt(SIZE)).equals(Compass.HORIZONTAL)) {
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
    private void putShipOnBoard(List<Field> randomizedShips, Ship ship) throws CantPlaceShipsException {

        Field startingPosition = getValidFieldForShip(ship);
        for (int i = 0; i < ship.getSize(); i++) {
            int currentIndexToBeMarked = startingPosition.getId() + (i * multiplier);
            Field currentField = board.getField(currentIndexToBeMarked);
            currentField.setStateOfField(StateOfField.OCCUPIED);
            ship.addField(currentField);
            randomizedShips.add(currentField);
            setNeighbourFieldsOfShipAsIllegal(startingPosition, ship);
        }
    }

    /**
     * draws a field from the board until it's fully valid for a ship to
     * be placed on. See private methods invoked inside:
     * <p>
     * {@link #fieldIsOccupied(Field startingPosition)}
     * {@link #checkIfFieldIsIllegal(Field startingPosition)}
     * {@link #checkIfShipCanFit(Field startingPosition, Ship ship)}
     * {@link #ifShipHasRoomToBePlaced(Ship ship, Field startingPosition)}
     *
     * @param ship to be placed on board
     * @return valid starting field for @param ship
     * <p>
     */
    private Field getValidFieldForShip(Ship ship) throws CantPlaceShipsException {
        Field startingPosition;
        do {
            counterOfAttempts++;
            if (counterOfAttempts >= 60)
                throw new CantPlaceShipsException("Couldn't find space for ship.");

            startingPosition = getRandomFieldFromBoard();
        }
        while (fieldIsOccupied(startingPosition) ||
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

    private boolean fieldIsOccupied(Field startingPosition) {
        return startingPosition.getStateOfField().equals(StateOfField.OCCUPIED);
    }

    private boolean ifShipHasRoomToBePlaced(Ship ship, Field startingPosition) {
        for (int i = 0; i < ship.getSize(); i++) {
            Field currentlyCheckedField = board.getField(startingPosition.getId() + i * multiplier);
            if (fieldIsOccupied(currentlyCheckedField) ||
                    checkIfFieldIsIllegal(currentlyCheckedField)) {
                return true;
            }
        }
        return false;
    }

    private Field getRandomFieldFromBoard() {
        return board.getField(ThreadLocalRandom.current().nextInt(board.getSize()));
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
     * depending if ship was placed horizontally or vertically {@link #setNeighboursOfFieldAsIllegal(Field, Ship)}
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
     * then ones on the left and right side of field (if not StateOfField.OCCUPIED)
     * {@link #changeStateOfFieldToIllegalSingleIndexBeforeAndAfter(Field currentField, int indexDifference, Ship)}
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
            Field currentField = board.getField((startingPosition.getId()) + j * multiplier);
            setNeighboursOfFieldAsIllegal(currentField, ship);
        }
    }

    private void setNeighboursOfFieldAsIllegal(Field field, Ship ship) {
        setFieldsAboveFieldAsIllegal(field, ship);
        setFieldsBelowFieldAsIllegal(field, ship);
    }

    private void setFieldsAboveFieldAsIllegal(Field field, Ship ship) {
        int rowJump = board.getDimension() * -1;
        int indexDifference = -1;
        iterateThroughSelectedFieldsAndMarkThemAsIllegal(field, rowJump, ship);
        changeStateOfFieldToIllegalSingleIndexBeforeAndAfter(field, indexDifference, ship);
    }

    private void setFieldsBelowFieldAsIllegal(Field field, Ship ship) {
        int rowJump = board.getDimension();
        int indexDifference = 1;
        iterateThroughSelectedFieldsAndMarkThemAsIllegal(field, rowJump, ship);
        changeStateOfFieldToIllegalSingleIndexBeforeAndAfter(field, indexDifference, ship);
    }

    private void changeStateOfFieldToIllegalSingleIndexBeforeAndAfter(Field currentField, int indexDifference, Ship ship) {
        if (indexDifference < 0 && currentField.getId() % board.getDimension() == 0) {
            return;
        } else if (indexDifference > 0 && currentField.getId() % board.getDimension() == board.getDimension() - 1) {
            return;
        } else {
            if (board.indexExists(currentField.getId() + indexDifference)) {
                Field startingPosition = board.getField(currentField.getId() + indexDifference);
                if (!(fieldIsOccupied(startingPosition))) {
                    startingPosition.setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
                    Ship currentShip = board.getFleet().getShipList().get(ship.getId());
                    currentShip.addToIllegalListOfFields(startingPosition);
                }
            }
        }
    }

    private void iterateThroughSelectedFieldsAndMarkThemAsIllegal(Field field, int rowJump, Ship ship) {
        if (field.getId() % board.getDimension() == 0) {
            changeStateWhenShipIsOnLeftBoarder(field, rowJump, ship);
        } else if (field.getId() % board.getDimension() == board.getDimension() - 1) {
            changeStateWhenShipIsOnRightBoarder(field, rowJump, ship);
        } else {
            for (int i = -1; i < 2; i++) {
                changeStateOfFieldToIllegal(field, rowJump, i, ship);
            }
        }
    }

    private void changeStateWhenShipIsOnRightBoarder(Field field, int rowJump, Ship ship) {
        for (int i = -1; i < 1; i++) {
            changeStateOfFieldToIllegal(field, rowJump, i, ship);
        }
    }

    private void changeStateWhenShipIsOnLeftBoarder(Field field, int rowJump, Ship ship) {
        for (int i = 0; i < 2; i++) {
            changeStateOfFieldToIllegal(field, rowJump, i, ship);
        }
    }

    private void changeStateOfFieldToIllegal(Field currentField, int rowJump, int index, Ship ship) {
        int indexOfFieldToChange = currentField.getId() + rowJump + index;

        if (board.indexExists(indexOfFieldToChange)) {
            Field fieldToUpdate = board.getField(indexOfFieldToChange);
            if (fieldIsOccupied(fieldToUpdate)) {
                return;
            }
            fieldToUpdate.setStateOfField(StateOfField.ILLEGAL_TO_PLACE);
            Ship currentShip = board.getFleet().getShipList().get(ship.getId());
            currentShip.addToIllegalListOfFields(fieldToUpdate);
        }
    }
}
