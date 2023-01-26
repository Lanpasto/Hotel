package model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Orders {

    private int id;
    private int userId;
    private Timestamp dateOfSettlement;
    private Timestamp dateOfOut;
    private Timestamp dateOfCreateOrder;
    private String image;
    private String statusRoom;
    private int roomId;
    private int totalPrice;
    private String status;
    private int orderRequestId;
    private String statusOfPay;

}
