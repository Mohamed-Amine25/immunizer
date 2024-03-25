package main.java.org.immunizer.microservices.monitor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.immunizer.microservices.monitor.testModelMapper;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;


public class testMonitor {

    private static final String KAFKA_BOOTSTRAP_SERVERS = "kafka:9092";
    private static final String GROUP_ID = "Monitor";
    private static final String TOPIC_PATTERN = "INV/.+";
    private static final int BATCH_DURATION = 100;

    public static void main(String[] args) throws Exception {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", KAFKA_BOOTSTRAP_SERVERS);
        kafkaParams.put("group.id", GROUP_ID);
        kafkaParams.put("enable.auto.commit", "true");
        kafkaParams.put("auto.commit.interval.ms", "1000");
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        

try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
    // Subscribe to topics using a pattern
    consumer.subscribe(Pattern.compile(TOPIC_PATTERN));
    
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(BATCH_DURATION));
        for (ConsumerRecord<String, String> record : records) {
           // Process the record and use Ignite cache
                    // Example: Cache the record value with the record key
                    if (record!=null) {
                        testModelMapper(record.key(), record.value());
                    }                   
      }
    }
}
}
}