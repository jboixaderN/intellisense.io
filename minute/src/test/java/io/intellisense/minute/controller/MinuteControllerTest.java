package io.intellisense.minute.controller;

import io.intellisense.minute.Messages;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MinuteControllerTest {

    @Autowired
    private MinuteController minuteController;
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();;

    @Test
    public void averageTest_Success(){
        ResponseEntity<String> response = postCall("{\"period\":\"10\"}");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void averageTest_NullError(){
        ResponseEntity<String> response = postCall(null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    @Test
    public void averageTest_NegativeError(){
        ResponseEntity<String> response = postCall("{\"period\":\"-1\"}");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        Assert.assertTrue(response.toString().contains(Messages.ERROR_MESSAGE_POSITIVE_NUMBER));
    }
    @Test
    public void averageTest_WrongNumberError(){
        ResponseEntity<String> response = postCall("{\"period\":\"A\"}");
        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    /**
     * Call to minute
     * @param requestString
     * @return
     */
    private ResponseEntity<String> postCall(String requestString){
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestString, headers);
        return restTemplate.postForEntity(Messages.ENDPOINT_MINUTE, entity, String.class);
    }
}
