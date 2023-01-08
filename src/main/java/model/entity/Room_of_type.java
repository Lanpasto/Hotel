package model.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room_of_type {
    private int id;
    private String type_of_room;
    private String guests;
    private String image;


}
