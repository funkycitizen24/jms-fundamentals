package org.lupasm.hm.guaranteedmessaging;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageConsumer {

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext(JMSContext.SESSION_TRANSACTED)) {

            JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
            Message message = consumer.receive();
            System.out.println(message);

            jmsContext.commit();

            Message message2 = consumer.receive();
            System.out.println(message2);

        }
    }
}