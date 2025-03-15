package com.ecom.shopping_cart.model;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Projects {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String title;

    @Column(length = 5000)
    private  String description;

    private LocalDate created_at;

    private  LocalDate updated_at;

    private String resetToken;
    @Transient
    private String shortDescription;

    public String getShortDescription() {
        if (description != null && description.length() > 100) {
            return description.substring(0, 100) + "...";
        }
        return description;
    }
}
