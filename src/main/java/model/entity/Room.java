package model.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    private int id;
    private int typeId;
    private String typeName;
    private Integer guests;
    private Integer price;
    private String status;
    private String image;

}
