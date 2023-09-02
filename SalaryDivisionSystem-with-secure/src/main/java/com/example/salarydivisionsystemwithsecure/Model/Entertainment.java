package com.example.salarydivisionsystemwithsecure.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Entertainment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "the month Number field is required ")
    @Range(min = 1, max = 12, message = "The Month number must be from 1-12")
    private Integer monthNumber;
    @NotEmpty(message = "the title field is required ")
    private String title;
    private Integer amountOfMoney;

    @ManyToOne
    @JoinColumn(name = "SalaryOwner_id",referencedColumnName ="user_id")
    @JsonIgnore
    private SalaryOwner salaryOwner;

    @ManyToOne
    @JoinColumn(name = "month_id",referencedColumnName ="id")
    @JsonIgnore
    private Month month;

}
