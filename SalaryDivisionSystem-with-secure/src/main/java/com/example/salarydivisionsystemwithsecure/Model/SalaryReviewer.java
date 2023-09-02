package com.example.salarydivisionsystemwithsecure.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class SalaryReviewer {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the name field is required")
    private String name;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName ="user_id")
    @JsonIgnore
    private SalaryOwner salaryOwners;

}
