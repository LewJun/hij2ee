package com.lewjun.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IloveuTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(IloveuTask.class);

    // 从 0分钟开始,每2分钟执行一次  http://cron.qqe2.com/
    @Scheduled(cron = "0 0/2 * * * ?")
    void sayIloveu() {
        LOGGER.info("I Love U");
    }
}
