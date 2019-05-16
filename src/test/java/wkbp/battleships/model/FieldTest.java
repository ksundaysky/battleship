package wkbp.battleships.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class FieldTest {

    private Field field = new Field(1,StateOfField.OCCUPIED);

    @Test
    private void testCreateField(){
        assertEquals(1,field.getId());
    }

    @Test
    private void testSetId(){

        field.setId(2);
        assertEquals(2,field.getId());
    }

}