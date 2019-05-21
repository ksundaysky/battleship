package wkbp.battleships.message.response;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class ResponseMessageTest {

    private ResponseMessage responseMessage = new ResponseMessage("message");

    @Test
    public void testSetMessage() {
        String expectedMessage = "new_message";
        responseMessage.setMessage(expectedMessage);
        String actualMessage = responseMessage.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void testGetMessage() {

        String expectedMessage = "message";
        String actualMessage = responseMessage.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}