package stmall.domain;

import java.util.*;
import lombok.*;
import stmall.domain.*;
import stmall.infra.AbstractEvent;

@Data
@ToString
public class DeliveryCompleted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String productName;
    private String productId;
    private Integer qty;
    private String address;
    private String status;
}
