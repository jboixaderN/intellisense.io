package io.intellisense.minute.service;

import io.intellisense.minute.Messages;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class MinuteServiceTest {
    @Autowired
    private MinuteServiceImpl minuteService;

    @Test
    public void testAverage_ZeroValue(){
        try{
            minuteService.calculateAverage(0);
        }catch (Exception e){
            Assert.assertEquals(Messages.ERROR_MESSAGE_POSITIVE_NUMBER, e.getMessage());
        }
    }

    @Test
    @Ignore
    /**
     * Test ignored.
     * It's ONLY for development purpose.
     * It helps to validate format for all periods possibles from 1 to length of the entries.
     */
    public void testAverage_AllValues(){
        try{
            for (int i=1; i< minuteService.getAsset().getTimeEntry().size(); i++){
                HashMap<String, Object> response = minuteService.calculateAverage(i);
                validateResponseFormat(response);
                Assert.assertEquals(minuteService.getAsset().getTimeEntry().size()/i, ((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("time").size());
            }
        }catch (Exception e){
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Test for most important periods (1,10,60 and 180)
     */
    public void testAverage_ImportantPeriods() {
        List<Integer> periods = new ArrayList<Integer>(List.of(1, 10, 60, 180));
        periods.forEach(x -> validateAverage(x));
    }

    @Test
    public void testAverage_180(){
        HashMap<String, Object> response = validateAverage(180);
        Assert.assertEquals(Double.valueOf("4.497578958095"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3000").get(0));
        Assert.assertEquals(Double.valueOf("51.549952167851664"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3001").get(0));
        Assert.assertEquals(Double.valueOf("1.8551680608583334"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3002").get(0));
        Assert.assertEquals(Double.valueOf("47.74842548144278"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3003").get(0));
        Assert.assertEquals(Double.valueOf("8.0000873506"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3004").get(0));
        Assert.assertEquals(Double.valueOf("44.80973707264"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3005").get(0));
        Assert.assertEquals("2021-06-28T04:15:00.000Z",((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("time").get(0));
    }
    @Test
    public void testAverage_60(){
        HashMap<String, Object> response = validateAverage(60);
        Assert.assertEquals(Double.valueOf("4.541613151428334"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3000").get(0));
        Assert.assertEquals(Double.valueOf("55.27627886208666"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3001").get(0));
        Assert.assertEquals(Double.valueOf("1.8646856463033332"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3002").get(0));
        Assert.assertEquals(Double.valueOf("47.947854209173336"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3003").get(0));
        Assert.assertEquals(Double.valueOf("7.738203378886666"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3004").get(0));
        Assert.assertEquals(Double.valueOf("45.664694765843336"),((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("3005").get(0));
        Assert.assertEquals("2021-06-28T04:15:00.000Z",((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("time").get(0));
    }
    private HashMap<String, Object> validateAverage(Integer period){
        try{
            HashMap<String, Object> response = minuteService.calculateAverage(period);
            validateResponseFormat(response);
            Assert.assertEquals(minuteService.getAsset().getTimeEntry().size()/period, ((HashMap<String, List> )response.get(minuteService.getAsset().getId())).get("time").size());
            return response;
        }catch (Exception e){
            Assert.fail(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Validate the response given after calculate the average by periods of time.
     * @param response
     */
    private void validateResponseFormat(HashMap<String, Object> response){
        //Response is not null
        Assert.assertNotNull(response);
        //ID is in the request
        Assert.assertNotNull(response.get(minuteService.getAsset().getId()));
        HashMap<String, List> entries = (HashMap<String, List> )response.get(minuteService.getAsset().getId());
        //Time map is in the response
        Assert.assertNotNull(entries.get("time"));
        //Has all the data structure
        Assert.assertEquals(minuteService.getAsset().getMetricEntries().size()+1, entries.size());
        //All maps has the same size
        for (Map.Entry<String, List> entry : entries.entrySet()) {
            Assert.assertEquals(entries.get("time").size(), ((List)entry.getValue()).size());
        }

    }
}
