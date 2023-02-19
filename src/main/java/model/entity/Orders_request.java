package model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders_request {

    int id;
    String type_of_room;

    int guests;

    Timestamp dateOfSettlement;

    Timestamp dateOfOut;

    Timestamp dateOfCreateRequest;
    int userId;
    String email;
    String name;

    String lastName;
    String status;
    String typeName;

    int idRoom;

}
