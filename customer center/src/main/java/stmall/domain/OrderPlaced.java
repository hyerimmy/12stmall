package stmall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;
import stmall.infra.AbstractEvent;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String productName;
    private String productId;
    private Integer qty;
    private String status;
    private String address;
}
