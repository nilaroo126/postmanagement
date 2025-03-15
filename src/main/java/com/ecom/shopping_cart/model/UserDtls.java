package com.ecom.shopping_cart.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class UserDtls  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    private String NRC;
    private String password;
    private String email;
    private String phone;
    private String role;
    @Transient
    private Boolean isEnable = true;
    private Boolean accountNonLocked =true;
    private Integer failedAttempt =0;
    private Date lockTime;
    private String resetToken;

}
