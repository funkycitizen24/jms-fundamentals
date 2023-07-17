package org.lupasm.hm.p2p.eligibilitycheck;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EligibilityCheckerApp {
    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {

        InitialContext initialContext = new InitialContext();
        Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext jmsContext = cf.createContext("eligibilityuser","eligibilitypass")){

            JMSConsumer jmsConsumer1= jmsContext.createConsumer(requestQueue);
            JMSConsumer jmsConsumer2= jmsContext.createConsumer(requestQueue);
//            jmsConsumer.setMessageListener(new EligibilityCheckListener());

            for(int i=1;i<=10;i++){
                System.out.println("Consumer 1: " + jmsConsumer1.receive());
                System.out.println("Consumer 2: " + jmsConsumer2.receive());
            }
//            Thread.sleep(10000);
        }
    }
}
