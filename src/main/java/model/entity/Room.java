package model.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {
     int id;
     int typeId;
     String typeName;
     Integer guests;
     Integer price;
     String status;
     String image;

     int fromPrice;

     int byPrice;
}
