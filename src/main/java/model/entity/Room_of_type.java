package model.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room_of_type {
     int id;
     String type_of_room;
     String guests;
     String image;


}
