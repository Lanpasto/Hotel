package model.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Orders_request {

    private int id;
    private String type_of_room;

    private int guests;

    private Timestamp dateOfSettlement;

    private Timestamp dateOfOut;

    private Timestamp dateOfCreateRequest;
    private int userId;
    private String email;
    private String name;

    private String lastName;
    private String status;
    private String typeName;

    private int idRoom;

}
