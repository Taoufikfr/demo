package com.example.demo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest {

    @Mock
    private Account accountMock;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testDepositEndpoint() throws Exception {
        doNothing().when(accountMock).deposit(anyInt(), anyString());

        mockMvc.perform(post("/account/deposit?amount=1000&date=10-01-2012"))
                .andExpect(status().isOk());

        verify(accountMock, times(1)).deposit(1000, "10-01-2012");
    }

    @Test
    public void testWithdrawEndpoint() throws Exception {
        doNothing().when(accountMock).withdraw(anyInt(), anyString());

        mockMvc.perform(post("/account/withdraw?amount=500&date=14-01-2012"))
                .andExpect(status().isOk());

        verify(accountMock, times(1)).withdraw(500, "14-01-2012");
    }

    @Test
    public void testStatementEndpoint() throws Exception {
        when(accountMock.getTransactions()).thenReturn(List.of(
                new Transaction("deposit", 1000, "10-01-2012", 1000),
                new Transaction("deposit", 2000, "13-01-2012", 3000)
        ));

        mockMvc.perform(get("/account/statement"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("13-01-2012"))
                .andExpect(jsonPath("$[0].amount").value(2000));

        verify(accountMock, times(1)).getTransactions();
    }
}
