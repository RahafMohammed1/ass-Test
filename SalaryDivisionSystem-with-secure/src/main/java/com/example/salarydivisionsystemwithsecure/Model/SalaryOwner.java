package com.example.salarydivisionsystemwithsecure.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class SalaryOwner {
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaryOwners")
    private Set<SalaryReviewer> salaryReviewers;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaryOwner")
    private Set<FinancialCommitment> financialCommitments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaryOwner")
    private Set<Month> months;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaryOwner")
    private Set<Entertainment> entertainments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaryOwners")
    private Set<SalaryDivisionSummery> salaryDivisionSummeries;

}
