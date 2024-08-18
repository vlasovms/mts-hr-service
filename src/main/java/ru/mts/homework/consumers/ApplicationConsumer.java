package ru.mts.homework.consumers;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.mts.homework.dto.Application;
import ru.mts.homework.services.ApplicationService;

@Component
@Data
public class ApplicationConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, Application> kafkaTemplate;
    @Autowired
    ApplicationService applicationService;

    @KafkaListener(topics = "application.hr.request", groupId = "consumer-3")
    public void consumeHRRequest(Application app) {
        logger.info("HR Service consume message from application.hr.request:  {}", app);
        app = applicationService.makeVacationDecision(app);
        kafkaTemplate.send("application.hr.result", app);
        logger.info("HR Decision sended to topic application.hr.result");
    }


}
