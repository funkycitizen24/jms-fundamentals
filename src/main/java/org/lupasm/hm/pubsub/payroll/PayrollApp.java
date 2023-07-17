package org.lupasm.hm.pubsub.payroll;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.lupasm.hm.pubsub.model.Employee;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PayrollApp {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Topic topic = (Topic) initialContext.lookup("topic/empTopic");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext jmsContext = cf.createContext()){
            JMSConsumer consumer = jmsContext.createConsumer(topic);
            Message message = consumer.receive();
            Employee employee = message.getBody(Employee.class);
            System.out.println(employee.getFirstName());

        }
    }
}
