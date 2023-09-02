package com.example.salarydivisionsystemwithsecure.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class SalaryDivisionSummery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "the month Number field is required ")
    @Column(columnDefinition = "Integer(20) unique")
    @Range(min = 1,max = 12,message ="The Month number must be from 1-12")
    private Integer month_number;
    @NotNull(message = "the Salary amount field is required ")
    private double salaryAmount;
    private double theAmountOfMoneyForFinancialCommitments=0;
    private double TheAmountOfMoneyForEntertainment=0;
    private double TheAmountOfMoneyForSaving=0;
    private double TheAmountOfExcessMoney=0;

    @ManyToOne
    @JoinColumn(name = "SalaryOwner_id",referencedColumnName ="user_id")
    @JsonIgnore
    private SalaryOwner salaryOwners;

    @ManyToOne
    @JoinColumn(name = "month_id",referencedColumnName ="id")
    @JsonIgnore
    private Month month;
}
