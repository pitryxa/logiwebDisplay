package logiweb.message;

import logiweb.bean.DisplayBean;
import logiweb.service.DisplayService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "Listener", activationConfig = {
               @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
               @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topic/Logiweb"),
               @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class Listener implements MessageListener {
    private final String UPDATE = "update";

    @Inject
    private DisplayBean displayBean;

    @Inject
    private DisplayService displayService;


    @Override
    public void onMessage(Message message) {
        try {
            if (message.getBody(String.class).equals(UPDATE)) {
                displayBean.updateDisplay(displayService.getDataForDisplay());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
