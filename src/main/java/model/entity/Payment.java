package model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Payment {
    private int id;
    private int ordersId;
    private String status;
    private int userIdpay;
}
