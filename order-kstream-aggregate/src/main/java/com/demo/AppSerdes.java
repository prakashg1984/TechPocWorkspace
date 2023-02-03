package com.demo;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import com.demo.kafka.examples.serde.JsonDeserializer;
import com.demo.kafka.examples.serde.JsonSerializer;
import com.demo.kafka.examples.types.DepartmentAggregate;
import com.demo.kafka.examples.types.Employee;

import java.util.HashMap;
import java.util.Map;

class AppSerdes extends Serdes {

    static final class EmployeeSerde extends WrapperSerde<Employee> {
        EmployeeSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<Employee> Employee() {
        EmployeeSerde serde = new EmployeeSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", Employee.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class DepartmentAggSerde extends WrapperSerde<DepartmentAggregate> {
        DepartmentAggSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static Serde<DepartmentAggregate> DepartmentAggregate() {
        DepartmentAggSerde serde = new DepartmentAggSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("specific.class.name", DepartmentAggregate.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }
}
