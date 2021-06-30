package io.intellisense.minute.model;

import io.intellisense.minute.Messages;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class MinuteRequest {

    @NotNull(message = Messages.ERROR_MESSAGE_PERIOD)
    @Min(value = 0, message = Messages.ERROR_MESSAGE_POSITIVE_NUMBER)
    private Integer period;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
