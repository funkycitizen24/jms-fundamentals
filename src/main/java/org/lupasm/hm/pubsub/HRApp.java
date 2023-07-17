package org.lupasm.hm.pubsub;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.lupasm.hm.pubsub.model.Employee;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HRApp {
    public static void main(String[] args) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Topic topic = (Topic) initialContext.lookup("topic/empTopic");

        try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
            JMSContext jmsContext = cf.createContext()){

            Employee employee = new Employee(123,"Miruna","Lupas","mirunalupas21@gmail.com","Junior Java Developer","0748524555");

            for(int i=1;i<=10;i++){
                jmsContext.createProducer().send(topic, employee);

            }

            System.out.println("Message sent");

        }
    }
}
