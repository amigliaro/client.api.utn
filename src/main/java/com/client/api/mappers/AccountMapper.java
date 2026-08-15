package com.client.api.mappers;

import com.client.api.dto.AccountExtended;
import com.client.api.models.Account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {


    public static AccountExtended AccounttoDTO(Account account) {
        if (account == null) return null;
        return AccountExtended.builder()
                .numeroCuenta(account.getNumeroCuenta())
                .moneda(account.getMoneda())
                .saldoPesos(account.getSaldo())
                .build();
    }

    public static List<AccountExtended> AccountToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::AccounttoDTO)
                .collect(Collectors.toList());
    }

    public static Account DTOToAccount(AccountExtended dto) {
        if (dto == null) return null;
        return Account.builder()
                .numeroCuenta(dto.getNumeroCuenta())
                .saldo(dto.getSaldo())
                .moneda(dto.getMoneda())
                .build();
    }

}