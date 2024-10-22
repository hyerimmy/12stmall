package stmall.domain;

import stmall.domain.OrderPlaced;
import stmall.domain.OrderCanceld;
import stmall.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;


@Entity
@Table(name="Order_table")
@Data

//<<< DDD / Aggregate Root
public class Order  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String userId;
    
    
    
    
    private String productName;
    
    
    
    
    private String productId;
    
    
    
    
    private Integer qty;
    
    
    
    
    private String status;
    
    
    
    
    private String address;

    @PostPersist
    public void onPostPersist(){
    stmall.external.OrderQuery orderQuery = new stmall.external.OrderQuery();
    // orderQuery.set??()        
      = OrderApplication.applicationContext
        .getBean(stmall.external.Service.class)
        .order(orderQuery);


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();



        OrderCanceld orderCanceld = new OrderCanceld(this);
        orderCanceld.publishAfterCommit();

    
    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }




//<<< Clean Arch / Port Method
    public static void notifyToWaitingUsers(StockIncreased stockIncreased){
        
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(stockIncreased.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
