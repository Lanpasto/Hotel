package model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Orders {

    private int id;
    private int userId;
    private Date dateOfSettlement;
    private Date dateOfOut;
    private Date dateOfCreateOrder;
    private int roomId;
    private String paymentStatus;
    private String typeOfRoom;}
