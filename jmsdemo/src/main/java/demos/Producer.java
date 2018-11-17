package demos;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@LocalBean
public class Producer {
    @Resource(name = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(name = "java:/jms/queue/DLQ")
    private Destination destination;

    @Schedule(hour = "*", minute = "*", second = "*/5", persistent = false)
    public void produceMessage() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);

            messageProducer.send(session.createTextMessage("Hello MDB"));
            System.out.println("------------------------------------------------");
            connection.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
