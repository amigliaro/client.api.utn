package com.client.api.services;

import com.client.api.dto.AccountExtended;
import com.client.api.exceptions.CustomException;
import com.client.api.models.Account;
import com.client.api.models.Client;
import com.client.api.repositories.AccountRepository;
import com.client.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final DolarService dolarService;

    public AccountService(AccountRepository accountrepository, ClientRepository clientRepository, DolarService dolarService) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountrepository;
        this.dolarService = dolarService;
    }

    public List<AccountExtended> listarCuentas() {
        List<AccountExtended> accountExtended =  new ArrayList<>();
        List<Account> accounList = accountRepository.findAll();

        for (Account account : accounList) {
            new AccountExtended();
            accountExtended.add(AccountExtended.builder()
                    .accountId(account.getAccountId())
                            .moneda(account.getMoneda())
                            .numeroCuenta(account.getNumeroCuenta())
                            .saldo(account.getSaldo())
                            .client(account.getClient())
                            .saldoPesos(account.getSaldo() * dolarService.obtenerDolarOficial().getVenta())
                            .fechaModificacion(account.getFechaModificacion())
                    .build()
            );
        }

        return accountExtended;
    }

    public Account getCuentaById(Long id) {

        if  (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        }
        return null;
    }

    public Account insertarCuenta(Long idCliente, Account cuenta) {

        Optional<Client> client = (clientRepository.findById(idCliente));

        if (client.isPresent()) {
            cuenta.setClient(client.get());
            cuenta.setSaldo(0.0);
            cuenta.setActivo(true);
            cuenta.setFechaCreacion(LocalDateTime.now());
            cuenta.setFechaModificacion(LocalDateTime.now());

            return accountRepository.save(cuenta);
        } else {
            throw new CustomException("No se encontró el cliente ingresado para la creación de la cuenta.");
        }
    }

    public Account modificarCuenta(Long idCuenta, Account cuenta) {
        Optional<Account> auxCuenta = accountRepository.findById(idCuenta);

        if (auxCuenta.isPresent()) {
            if (cuenta.getNumeroCuenta() != null) auxCuenta.get().setNumeroCuenta(cuenta.getNumeroCuenta());
            if (cuenta.getMoneda() != null) auxCuenta.get().setMoneda(cuenta.getMoneda());
            if (cuenta.getSaldo() != null) auxCuenta.get().setSaldo(cuenta.getSaldo());
            if (cuenta.getActivo() != null) auxCuenta.get().setActivo(cuenta.getActivo());
            if (cuenta.getFechaModificacion() != null) auxCuenta.get().setFechaModificacion(cuenta.getFechaModificacion());

            return accountRepository.save(auxCuenta.get());
        } else {
            throw new CustomException("No se encontró la cuenta para el id ingresado.");
        }
    }

    public void eliminarCuenta(Long idCuenta) {
        accountRepository.deleteById(idCuenta);
    }
}


