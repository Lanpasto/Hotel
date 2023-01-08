package model.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private int roleId;

}