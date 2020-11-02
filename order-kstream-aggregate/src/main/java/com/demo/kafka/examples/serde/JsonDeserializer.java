package com.demo.kafka.examples.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> className;

    public JsonDeserializer() {

    }

    /**
     * Set the specific Java Object Class Name
     *
     * @param props set specific.class.name to your specific Java Class Name
     * @param isKey set it to false
     */
    @SuppressWarnings("unchecked")
    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
        className = (Class<T>) props.get("specific.class.name");
    }

    /**
     * Deserialize to a POJO
     *
     * @param topic topic name
     * @param data  message bytes
     * @return Specific Java Object
     */
    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, className);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {
        //nothing to close
    }
}
