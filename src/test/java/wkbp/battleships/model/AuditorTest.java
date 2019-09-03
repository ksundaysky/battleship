package wkbp.battleships.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Patryk Kucharski
 */
public class AuditorTest {

    @Test(dataProvider = "getCoordinatesDP")
    public void testGetCoordinatesOfField(int id, String expected) {
        Auditor auditor = new Auditor();
        Field field = new Field(id, null, false);
        String actual = auditor.getCoordinatesOfField(field);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] getCoordinatesDP() {
        return new Object[][]{
                {0, "A1"},
                {11, "B2"},
                {22, "C3"},
                {33, "D4"},
                {44, "E5"},
                {55, "F6"},
                {66, "G7"},
                {77, "H8"},
                {88, "I9"},
                {99, "J10"},
        };
    }
}