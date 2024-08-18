package ru.mts.homework.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import ru.mts.homework.dto.Application;

public class ApplicationSerializer implements Serializer<Application> {
    @Override
    public byte[] serialize(String arg0, Application application) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            retVal = objectMapper.writeValueAsString(application).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

}
