package com.example.salarydivisionsystemwithsecure.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class FinancialCommitment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "the month Number field is required ")
    @Column(columnDefinition = "Integer(20) unique")
    @Range(min = 1, max = 12, message = "The Month number must be from 1-12")
    private Integer month_number;
    @NotNull(message = "the title Of Commitment field is required ")
    private String titleOfCommitment;
    @NotNull(message = "the total Value Of Commitment field is required ")
    private Integer totalValue;
    @Value("not complete")
    private String status;

    @ManyToOne
    @JoinColumn(name = "SalaryOwner_id", referencedColumnName = "user_id")
    @JsonIgnore
    private SalaryOwner salaryOwner;

    @ManyToOne
    @JoinColumn(name = "month_id", referencedColumnName = "id")
    @JsonIgnore
    private Month month;
}
