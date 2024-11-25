package com.bothBEandFE.ecom.Entity;

import com.bothBEandFE.ecom.Enums.UserRole;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

}
