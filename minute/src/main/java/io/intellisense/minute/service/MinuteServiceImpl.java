package io.intellisense.minute.service;

import io.intellisense.minute.Messages;
import io.intellisense.minute.model.Asset;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MinuteServiceImpl implements MinuteService {
    //Json data load
    private Asset asset;

    /**
     * Get json file data and map into asset POJO
     */
    @PostConstruct
    public void inicializate(){
        asset  = DataReaderConverter.readData();
    }

    public HashMap<String, Object> calculateAverage(int period) throws Exception{
        if(period<=0){
            throw new Exception(Messages.ERROR_MESSAGE_POSITIVE_NUMBER);
        }
        HashMap<String, Object> minuteResponse = new HashMap<String, Object>();
        Map<String, Object> metrics = new HashMap<String, Object>();

        for (Map.Entry<String, Map> entry : asset.getMetricEntries().entrySet()) {
            metrics.put(entry.getKey(), calculateAvegareByPeriod((ArrayList)entry.getValue(), period));
        }
        metrics.put("time", getTimeEntry(period));
        minuteResponse.put(this.asset.getId(), metrics);
        return minuteResponse;
    }
    private List<Double> calculateAvegareByPeriod(ArrayList<Double> list, int period){
        ArrayList<Double> retval = new ArrayList<Double>();
        int indexFrom = 0;
        int indexTo = period;
        while (indexTo <=list.size()){
            retval.add(calculateAverage(list.subList(indexFrom, indexTo)));
            indexFrom = indexTo;
            indexTo+=period;
        }
        return retval;
    }
    private List<String> getTimeEntry(int period){
        ArrayList<String> retval = new ArrayList<String>();
        for (int i =0; i+period<=this.asset.getTimeEntry().size(); i+=period ){
            retval.add(this.asset.getTimeEntry().get(i));
        }
        return retval;
    }

    /**
     * On the description of the exercice there is nothing about:
     *  - How to work with nulls
     *  - Format number. How many decimals?
     * @param list
     * @return
     */
    private Double calculateAverage(List <Double> list) {
        return list.stream()
                .mapToDouble(d -> d == null ? 0 : d)
                .average()
                .orElse(0.0);
    }

    public Asset getAsset() {
        return asset;
    }
}
