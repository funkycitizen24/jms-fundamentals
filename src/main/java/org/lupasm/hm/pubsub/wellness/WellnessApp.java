package org.lupasm.hm.pubsub.wellness;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.lupasm.hm.pubsub.model.Employee;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WellnessApp {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Topic topic = (Topic) initialContext.lookup("topic/empTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createSharedConsumer(topic, "sharedConsumer");
            JMSConsumer consumer2 = jmsContext.createSharedConsumer(topic, "sharedConsumer");

            for (int i = 1; i <= 10; i += 2) {
                Message message = consumer.receive();
                Employee employee = message.getBody(Employee.class);
                System.out.println("Consumer 1: " + employee.getFirstName());

                Message message2 = consumer2.receive();
                Employee employee2 = message2.getBody(Employee.class);
                System.out.println("Consumer 2: " + employee2.getFirstName());
            }

        }
    }
}
