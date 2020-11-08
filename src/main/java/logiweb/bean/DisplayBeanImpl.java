package logiweb.bean;

import logiweb.dto.DisplayDto;
import logiweb.service.DisplayService;
import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@ManagedBean(name = "display")
//@Named("display")
public class DisplayBeanImpl implements DisplayBean {

    private DisplayDto displayDto;

    private final String CDI_PUSH_TOPIC = "pushCdi";
    private TopicKey topicKey = new TopicKey(CDI_PUSH_TOPIC);
    private TopicsContext topicsContext;

    @Inject
    private DisplayService displayService;

    @PostConstruct
    public void initDisplay() {
        displayDto = displayService.getDataForDisplay();
//        eventDTOS.addAll(eventService.getEvents());
//        logger.info("Successfully Init Storage, size " + eventDTOS.size());

        topicsContext = TopicsContext.lookup();
        topicsContext.getOrCreateTopic(topicKey);
    }

    public DisplayDto getDisplayDto() {
        return displayDto;
    }

    public String getCdiPushTopic() {
        return CDI_PUSH_TOPIC;
    }

    private void sendUpdate() {
        try {
            topicsContext.publish(topicKey, displayDto);
//            topicsContext.publish(topicKey, "update");
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDisplay(DisplayDto displayDto) {
        this.displayDto = displayDto;
        sendUpdate();
    }
}

