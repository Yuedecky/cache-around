package com.broad.data.stopwatch;


import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ElapsedExample {


    private static final Logger logger = LoggerFactory.getLogger(ElapsedExample.class);

    public static void main(String[] args) throws Exception {
        processOrderNo(UUID.randomUUID().toString());
    }


    /**
     * @param orderNo 订单好
     * @throws InterruptedException
     */
    private static void processOrderNo(String orderNo) throws InterruptedException {
        logger.info("start process order,the orderNo is:{}", orderNo);
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.MILLISECONDS.sleep(20);

        logger.info("process the orderNo: [{}] successful and elapsed [{}]", orderNo, stopwatch.stop().elapsed());
    }
}
