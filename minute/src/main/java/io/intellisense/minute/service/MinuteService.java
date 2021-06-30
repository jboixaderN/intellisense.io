package io.intellisense.minute.service;

import java.util.HashMap;

public interface MinuteService {
    /**
     * Method for calulcate the average by periods
     * @param period
     * @return
     * @throws Exception
     */
    HashMap<String, Object> calculateAverage(int period)throws Exception;
}
