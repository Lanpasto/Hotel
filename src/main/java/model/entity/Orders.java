package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {

     int id;
     int userId;
     Timestamp dateOfSettlement;
     Timestamp dateOfOut;
     Timestamp dateOfCreateOrder;
     String image;
     String statusRoom;
     int roomId;
     int totalPrice;
     String status;
     int orderRequestId;
     String statusOfPay;

}
