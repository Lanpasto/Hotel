package model.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
     int id;
     String email;
     String password;
     String first_name;
     String last_name;
     int roleId;

}