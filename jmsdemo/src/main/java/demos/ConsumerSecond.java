package demos;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "ConsumerSecond",
        activationConfig = {@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/DLQ")})
public class ConsumerSecond implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText() + this.getClass().toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
