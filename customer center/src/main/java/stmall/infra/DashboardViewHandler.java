package stmall.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import stmall.config.kafka.KafkaProcessor;
import stmall.domain.*;

@Service
public class DashboardViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private DashboardRepository dashboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            Dashboard dashboard = new Dashboard();
            // view 객체에 이벤트의 Value 를 set 함
            dashboard.setOrderId(orderPlaced.getId());
            dashboard.setUserId(orderPlaced.getUserId());
            dashboard.setProductId(orderPlaced.getProductId());
            dashboard.setProductName(orderPlaced.getProductName());
            dashboard.setQty(orderPlaced.getQty());
            dashboard.setStatus(orderPlaced.getStatus());
            // view 레파지 토리에 save
            dashboardRepository.save(dashboard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
