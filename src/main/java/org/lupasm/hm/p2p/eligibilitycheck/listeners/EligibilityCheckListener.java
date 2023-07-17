package org.lupasm.hm.p2p.eligibilitycheck.listeners;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.lupasm.hm.p2p.model.Patient;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EligibilityCheckListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = (ObjectMessage) message;
        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
             JMSContext jmsContext = cf.createContext()) {

            InitialContext initialContext = new InitialContext();
            Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueue");

            MapMessage replyMessage = jmsContext.createMapMessage();
            Patient patient = (Patient) objectMessage.getObject();

            String insuranceProvider = patient.getInsuranceProvider();
            System.out.println("Insurance provider: " + insuranceProvider);
            if (patient.getInsuranceProvider().equals("Blue Cross Blue Shield") || insuranceProvider.equals("United Health")) {
                System.out.println("Patients copay is: "+ patient.getCopay());
                System.out.println("Amount to be paid: " + patient.getAmountToBePayed());
                if (patient.getCopay() < 40 && patient.getAmountToBePayed() < 1000) {
                    replyMessage.setBoolean("Eligible", true);
                } else {
                    replyMessage.setBoolean("Eligible", false);
                }
                JMSProducer producer = jmsContext.createProducer();
                producer.send(replyQueue,replyMessage);
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
