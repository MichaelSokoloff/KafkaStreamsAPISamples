import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaTopicCreator {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.155:9092");

        AdminClient adminClient = AdminClient.create(properties);

        NewTopic newTopic = new NewTopic("input-topic", 3, (short) 1);

        CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(newTopic));
        createTopicsResult.all().get();

        NewTopic newTopic2 = new NewTopic("output-topic", 3, (short) 1);

        CreateTopicsResult createTopicsResult2 = adminClient.createTopics(Collections.singleton(newTopic2));
        createTopicsResult.all().get();
        adminClient.close();
    }
}
