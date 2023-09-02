package com.example.salarydivisionsystemwithsecure.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "the month Number field is required ")
    @Column(columnDefinition = "unique")
    @Range(min = 1,max = 12,message ="The Month number must be from 1-12")
    private Integer month_number;
    @ManyToOne
    @JoinColumn(name = "SalaryOwner_id",referencedColumnName ="user_id")
    @JsonIgnore
    private SalaryOwner salaryOwner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "month")
    private Set<FinancialCommitment>financialCommitments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "month")
    private Set<Entertainment>entertainments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "month")
    private Set<SalaryDivisionSummery>salaryDivisionSummeries;
}
