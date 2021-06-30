package io.intellisense.minute.service;

import io.intellisense.minute.model.Asset;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


public class DataReaderConverterTest {
    private DataReaderConverter dataReaderConverter;

    @Before
    public void setUp(){
        dataReaderConverter = new DataReaderConverter();
    }

    @Test
    public void loadDataTest(){
        Asset asset = dataReaderConverter.readData();
        Assert.assertNotNull(asset);
        Assert.assertEquals("660", asset.getId());
        Assert.assertNotNull(asset.getMetricEntries());
        Assert.assertNotNull(asset.getTimeEntry());
        //Same size
        for (Map.Entry<String, Map> entry : asset.getMetricEntries().entrySet()) {
            Assert.assertEquals(asset.getTimeEntry().size(), ((List)entry.getValue()).size());
        }
    }
}
