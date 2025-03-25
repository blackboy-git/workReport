package com.blackboy.domain;

import lombok.Data;

@Data
public class Statistics {
    //周报已经填写次数
    private Long filledCount;

    //周报应填写次数
    private Long shouldFilledCount;

    //周报未填写次数
    private Long unfilledCount;


    public Statistics(Long filledCount, Long shouldFilledCount) {
        this.filledCount = filledCount;
        this.shouldFilledCount = shouldFilledCount;
        this.unfilledCount = shouldFilledCount - filledCount;
    }
}
