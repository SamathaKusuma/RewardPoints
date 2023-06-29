package com.samatha.rewardsProgram.service;

import com.samatha.rewardsProgram.dto.CustomerDTO;
import com.samatha.rewardsProgram.dto.Rewards;
import com.samatha.rewardsProgram.dto.TransactionDTO;
import com.samatha.rewardsProgram.model.Customer;
import com.samatha.rewardsProgram.repository.CustomerRepository;
import com.samatha.rewardsProgram.service.impl.CustomerServiceImpl;
import com.samatha.rewardsProgram.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;


@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceImplTest {

    @Mock
    DataBuilder dataBuilder;

    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    @Mock
    CustomerRepository customerRepository;

    @Mock
    private TransactionServiceImpl transactionService;

    @Mock
    Customer customer;

    private TransactionDTO transactionDTO = new TransactionDTO();

    private CustomerDTO customerDTO = new CustomerDTO();

    @BeforeEach
    public void setup() {
        CustomerDTO customer = dataBuilder.constructCustomerDTOWithCustomerName("test");
        transactionDTO.setCustomerDTO(customer);
        transactionDTO.setTransactionDate(LocalDateTime.of(2023,
                Month.APRIL, 1, 19, 30, 40));
        transactionDTO.setTransactionAmount(Long.valueOf(90));
        transactionDTO.setTransactionID(1000l);

    }

    @Test
    void getRewardsTest() {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(transactionDTO);
        doReturn(transactionDTOList).when(transactionService).getThreeMonthTransactions();
        List<Rewards> rewards = customerService.getRewards();
        assertEquals(0, rewards.size());
    }

    @Test
    void getRewardsForCustomerTest(){
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionDTOList.add(transactionDTO);
        doReturn(Optional.of(customer)).when(customerRepository).findById(Long.valueOf(100));
        doReturn(transactionDTOList).when(transactionService).getThreeMonthTransactions();
        Rewards rewards = customerService.getRewardsForCustomer(Long.valueOf(100));
        assertNotNull(rewards);
    }
}
