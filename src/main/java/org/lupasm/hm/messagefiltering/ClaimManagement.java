package org.lupasm.hm.messagefiltering;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClaimManagement {

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/claimQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            JMSConsumer consumer = jmsContext.createConsumer(requestQueue, "doctorName LIKE 'Johna' OR JMSPriority BETWEEN 4 AND 9");
            JMSProducer producer = jmsContext.createProducer();

            ObjectMessage objectMessage = jmsContext.createObjectMessage();
//            objectMessage.setIntProperty("hospitalID",1);
//            objectMessage.setDoubleProperty("claimAmount",1000.0);
            objectMessage.setStringProperty("doctorName","John");
            Claim claim = new Claim(1, "John", "gyno", "Blue Cross", 1000.0);
            objectMessage.setObject(claim);

            producer.send(requestQueue,objectMessage);

            Claim receivedBody = consumer.receiveBody(Claim.class);
            System.out.println(receivedBody.getClaimAmount());
        }
    }
}
