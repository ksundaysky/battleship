package wkbp.battleships.businesslogic;

import wkbp.battleships.model.*;

import java.util.*;

/**
 * @author Wiktor Rup
 */
public class ShipsRandomize {

    //TODO:: dokumentacja do tych dwoch pol;
    private static final int VERTICAL_MULTIPLIER = 10;
    private static final int HORIZONTAL_MULTIPLIER = 1;
    private static final List<Compass> VALUES =
            Collections.unmodifiableList(Arrays.asList(Compass.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private Board board;
    private Fleet fleet;
//    private boolean isHorizontal = false;

    public ShipsRandomize(Board board, Fleet fleet) {
        this.fleet = fleet;
        this.board = board;
    }


    public List<Field> randomizeShips() {
        List<Field> randomizedShips = new ArrayList<>();
        Field startingPosition;
        for (Ship ship : fleet.getShipList()) {
            if (VALUES.get(RANDOM.nextInt(SIZE)).equals(Compass.HORIZONTAL)) {
//                isHorizontal = true;
                do {
                    startingPosition = getRandomFieldFromBoard();
                }
                while (checkIfFieldIsOccupied(startingPosition) ||
                        checkIfFieldIsIllegal(startingPosition) ||
                        checkIfShipCanFit(startingPosition, ship, true) ||
                        ifShipHasRoomToBePlaced(ship, startingPosition, true));

                for (int i = 0; i < ship.getSize(); i++) {
                    board.getFieldList().get(startingPosition.getId() + i).setStateOfField(StateOfField.OCCUPIED); // todo zmień stan pola
                    randomizedShips.add(board.getFieldList().get(startingPosition.getId() + i)); // TODO: 13.05.19 zapisz do listy do wysłania
                }

                setNeighbourFieldsOfShipAsIllegal(startingPosition, ship, true);

            } else {
                do {
                    startingPosition = getRandomFieldFromBoard();
                }
                while (checkIfFieldIsOccupied(startingPosition) ||
                        checkIfFieldIsIllegal(startingPosition) ||
                        checkIfShipCanFit(startingPosition, ship, false) ||
                        ifShipHasRoomToBePlaced(ship, startingPosition, false));

                for (int i = 0; i < ship.getSize(); i++) {
                    board.getFieldList().get(startingPosition.getId() + (i * 10)).setStateOfField(StateOfField.OCCUPIED);
                    randomizedShips.add(board.getFieldList().get(startingPosition.getId() + (i * 10)));
                }
                setNeighbourFieldsOfShipAsIllegal(startingPosition, ship, false);
            }
        }
        return randomizedShips;
    }

    private Field getRandomFieldFromBoard() {
        return board.getFieldList().get(RANDOM.nextInt(board.getFieldList().size()));
    }

    private void setNeighbourFieldsOfShipAsIllegal(Field startingPosition, Ship ship, boolean isHorizontal) {
        int multiplier = VERTICAL_MULTIPLIER;
        if (isHorizontal) {
            multiplier = HORIZONTAL_MULTIPLIER;
        }

        for (int j = 0; j < ship.getSize(); j++) {
            Field currentField = board.getFieldList().get((startingPosition.getId()) + j * multiplier);
                setNeighboursOfFieldAsIllegal(currentField, true);
                setNeighboursOfFieldAsIllegal(currentField, false);
        }
    }

    private void setNeighboursOfFieldAsIllegal(Field currentField, boolean areFieldsAboveCurrentField) {
        int columns = board.getColumns();
        int indexDifference = 1;
        if (areFieldsAboveCurrentField){
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


    private boolean checkIfShipCanFit(Field startingPosition, Ship ship, boolean isHorizontal) {
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

    private boolean ifShipHasRoomToBePlaced(Ship ship, Field startingPosition, boolean isHorizontal) {
        int multiplier = VERTICAL_MULTIPLIER;
        if (isHorizontal) {
            multiplier = HORIZONTAL_MULTIPLIER;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            if (checkIfFieldIsOccupied(board.getFieldList().get(startingPosition.getId() + i * multiplier)) ||
                    checkIfFieldIsIllegal(board.getFieldList().get(startingPosition.getId() + i * multiplier))) {
                return true;
            }
        }
        return false;
    }

}
