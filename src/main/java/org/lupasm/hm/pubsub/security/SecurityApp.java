package org.lupasm.hm.pubsub.security;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.shaded.org.jgroups.MessageListener;
import org.lupasm.hm.pubsub.model.Employee;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SecurityApp {
    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
        InitialContext initialContext = new InitialContext();
        Topic topic = (Topic) initialContext.lookup("topic/empTopic");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext jmsContext = cf.createContext()){

            jmsContext.setClientID("securityApp");

            JMSConsumer consumer = jmsContext.createDurableConsumer(topic,"subscription1");
            consumer.close();

            Thread.sleep(10000);
            consumer = jmsContext.createDurableConsumer(topic,"subscription1");
            Message message = consumer.receive();
            Employee employee = message.getBody(Employee.class);
            System.out.println(employee.getFirstName());

            consumer.close();
            jmsContext.unsubscribe("subscription1");

        }
    }
}
