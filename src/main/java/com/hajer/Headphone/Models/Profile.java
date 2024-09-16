package com.hajer.Headphone.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile extends EntityAbstract {


    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String phoneNumber;

    private String description;




    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
