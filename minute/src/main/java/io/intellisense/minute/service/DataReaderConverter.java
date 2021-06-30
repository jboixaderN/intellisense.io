package io.intellisense.minute.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.intellisense.minute.MinuteApplication;
import io.intellisense.minute.model.Asset;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class DataReaderConverter {

    public static String jsonFilePath = "/testData.json";
    public static Asset readData(){
        Asset asset = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = MinuteApplication.class.getResourceAsStream(jsonFilePath);
            Map<String, Map> map = mapper.readValue(inputStream, Map.class);
            asset = new Asset();
            //Get AssetId
            asset.setId(map.keySet().stream().findFirst().get());
            //Set time Entry
            asset.setTimeEntry((List<String>)map.get(asset.getId()).remove("time"));
            //Set Metric entry
            asset.setMetricEntries(map.get(asset.getId()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return asset;
    }

}
