package com.deliveroo.expressionengine;

import com.deliveroo.expressionengine.processor.CronExpressionProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpressionEngineApplication {

    public static void main(String[] args) {
        if (args.length == 1 && args[0].isEmpty()) throw new RuntimeException("Should provide at least one argument!");

        // invoking cron processor
        new CronExpressionProcessor(args[0]);
    }
}
