package demos;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "ConsumerFirst",
        activationConfig = {@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/DLQ")})
public class ConsumerFirst implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        System.out.println(textMessage.toString() + this.getClass().toString());
    }
}
