package wkbp.battleships.message.response;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class ResponseMessageTest {

    private ResponseMessage responseMessage = new ResponseMessage("message");

    @Test
    public void testGetResponseMessage(){
        assertEquals("message",responseMessage.getMessage());
    }


    @Test
    public void testSetResponseMessage(){
        responseMessage.setMessage("new_message");
        assertEquals("new_message",responseMessage.getMessage());

    }

}