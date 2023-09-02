package com.example.salarydivisionsystemwithsecure;

import com.example.salarydivisionsystemwithsecure.Model.Entertainment;
import com.example.salarydivisionsystemwithsecure.Model.Month;
import com.example.salarydivisionsystemwithsecure.Model.SalaryOwner;
import com.example.salarydivisionsystemwithsecure.Model.User;
import com.example.salarydivisionsystemwithsecure.Repository.EntertainmentRepository;
import com.example.salarydivisionsystemwithsecure.Repository.MonthRepository;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryDivisionSummeryRepo;
import com.example.salarydivisionsystemwithsecure.Repository.SalaryOwnerRepository;
import com.example.salarydivisionsystemwithsecure.Service.EntertainmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntertainmentServiceTest {

    @InjectMocks
    EntertainmentService entertainmentService;

    @Mock
    SalaryOwnerRepository salaryOwnerRepository;
    @Mock
    EntertainmentRepository entertainmentRepository;
    @Mock
    MonthRepository monthRepository;
    @Mock
    SalaryDivisionSummeryRepo salaryDivisionSummeryRepo;

    User user;

    Month month;
    SalaryOwner salaryOwner;
    Entertainment entertainment1;
    Entertainment entertainment2;
    Entertainment entertainment3;

    List<Entertainment> entertainments;

    @BeforeEach
    void SetUp()
    {
        user=new User(null,"RahafMohammed11","1234","OWNER",null,null);
        month=new Month(null,1,null,null,null,null);
        salaryOwner=new SalaryOwner(null,"Rahaf",user,null,null,null,null,null);
        entertainment1=new Entertainment(null,1,"pay doll",50,salaryOwner,null);
        entertainment2=new Entertainment(null,1,"pay juz",50,salaryOwner,null);
        entertainment3=new Entertainment(null,1,"pay T-shirt",50,salaryOwner,null);
        entertainments=new ArrayList<>();
        entertainments.add(entertainment1);
        entertainments.add(entertainment2);
        entertainments.add(entertainment3);
    }
    @Test
    public void getAllMyEntertainmentTest()
    {
        when(salaryOwnerRepository.findSalaryOwnerById(user.getId())).thenReturn(salaryOwner);
        when(entertainmentRepository.findAllBySalaryOwner(salaryOwner)).thenReturn(entertainments);

        List<Entertainment> entertainmentList=entertainmentService.getAllMyEntertainment(user.getId());
        Assertions.assertEquals(entertainmentList,entertainments);

        verify(salaryOwnerRepository,times(1)).findSalaryOwnerById(user.getId());
        verify(entertainmentRepository,times(1)).findAllBySalaryOwner(salaryOwner);
    }
    @Test
    public void addEntertainment()
    {
        when(salaryOwnerRepository.findSalaryOwnerById(user.getId())).thenReturn(salaryOwner);
        when(monthRepository.findMonthById(month.getId())).thenReturn(month);

        entertainmentService.addEntertainment(user.getId(),entertainment1,month.getId());

        verify(salaryOwnerRepository,times(1)).findSalaryOwnerById(user.getId());
        verify(monthRepository,times(1)).findMonthById(month.getId());
        verify(entertainmentRepository,times(1)).save(entertainment1);
    }

    @Test
    public void updateEntertainment()
    {
        when(entertainmentRepository.findEntertainmentById(entertainment1.getId())).thenReturn(entertainment1);

        entertainmentService.updateEntertainment(user.getId(),entertainment2,entertainment1.getId());

        verify(entertainmentRepository,times(1)).findEntertainmentById(entertainment1.getId());
        verify(entertainmentRepository,times(1)).save(entertainment2);
    }

    @Test
   public void  deleteEntertainment()
   {
       when(entertainmentRepository.findEntertainmentById(entertainment1.getId())).thenReturn(entertainment1);
       entertainmentService.deleteEntertainment(user.getId(),entertainment1.getId());
       verify(entertainmentRepository,times(1)).findEntertainmentById(entertainment1.getId());
       verify(entertainmentRepository,times(1)).delete(entertainment1);

   }



}
