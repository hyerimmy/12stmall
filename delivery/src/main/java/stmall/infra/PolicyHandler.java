package stmall.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_StartDelivery(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener StartDelivery : " + orderPlaced + "\n\n"
        );

        // Comments //
        //- 계약한 CJ에 배송지시를 하고,
        // - 고객에게 배송 시작을 알리고,
        // - 우리 장부에도 keeping하고, ...

        // Sample Logic //
        Delivery.startDelivery(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCanceld'"
    )
    public void wheneverOrderCanceld_CancelDelivery(
        @Payload OrderCanceld orderCanceld
    ) {
        OrderCanceld event = orderCanceld;
        System.out.println(
            "\n\n##### listener CancelDelivery : " + orderCanceld + "\n\n"
        );

        // Sample Logic //
        Delivery.cancelDelivery(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
