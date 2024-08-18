package ru.mts.homework.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import ru.mts.homework.dto.Application;

public class ApplicationDeserializer implements Deserializer<Application> {
    @Override
    public Application deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Application application = null;
        try {
            application = mapper.readValue(bytes, Application.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return application;
    }
}
