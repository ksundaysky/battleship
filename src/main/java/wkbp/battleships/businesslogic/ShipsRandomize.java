package wkbp.battleships.businesslogic;

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
    private boolean isHorizontal = false;
    private int multiplier = 1;

    public ShipsRandomize(Board board, Fleet fleet) {
        this.fleet = fleet;
        this.board = board;
    }

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

    private void putShipOnBoard(List<Field> randomizedShips, Ship ship) {
        Field startingPosition = getValidFieldForShip(ship);

        for (int i = 0; i < ship.getSize(); i++) {
            board.getFieldList().get(startingPosition.getId() + i * multiplier).setStateOfField(StateOfField.OCCUPIED); // todo zmień stan pola
            randomizedShips.add(board.getFieldList().get(startingPosition.getId() + i * multiplier)); // TODO: 13.05.19 zapisz do listy do wysłania
        }
        setNeighbourFieldsOfShipAsIllegal(startingPosition, ship);
    }

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

    private Field getRandomFieldFromBoard() {
        return board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
    }

    private void setNeighbourFieldsOfShipAsIllegal(Field startingPosition, Ship ship) {
        for (int j = 0; j < ship.getSize(); j++) {
            Field currentField = board.getFieldList().get((startingPosition.getId()) + j * multiplier);
            setNeighboursOfFieldAsIllegal(currentField, true);
            setNeighboursOfFieldAsIllegal(currentField, false);
        }
    }

    private void setNeighboursOfFieldAsIllegal(Field currentField, boolean areFieldsAboveCurrentField) {
        int columns = board.getColumns();
        int indexDifference = 1;
        if (areFieldsAboveCurrentField) {
            columns *= -1;
            indexDifference = -1;
        }
        for (int i = -1; i < 2; i++) {
            changeStateOfFieldToIllegal(currentField, columns, i); // TODO: 13.05.19 ustawia pola nad i pod danym polem na illegal
        }
        changeStateOfFieldToIllegalSingleIndexBeforeOrAfter(currentField, indexDifference); // TODO: 13.05.19 ustawia poprzedni i następny index pola jako illegal
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


    private boolean checkIfShipCanFit(Field startingPosition, Ship ship) {
        if (isHorizontal) {
            return board.getColumns() - (ship.getSize()) < startingPosition.getId() % board.getColumns();
        }
        return board.getColumns() - ship.getSize() < startingPosition.getId() / board.getColumns();
    }

    private boolean checkIfFieldIsIllegal(Field startingPosition) {
        return startingPosition.getStateOfField().equals(StateOfField.ILLEGAL_TO_PLACE);
    }

    private boolean checkIfFieldIsOccupied(Field startingPosition) {
        return startingPosition.getStateOfField().equals(StateOfField.OCCUPIED);
    }

    private boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
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

}
