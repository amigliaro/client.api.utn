package com.client.api.services;

import com.client.api.models.Account;
import com.client.api.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> listarCuentas() {
        return accountRepository.findAll();
    }

    public Account getCuentaById(Long id) {

        if  (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        }
        return null;
    }

    public Account insertarCuenta(Account cuenta) {
        return accountRepository.save(cuenta);
    }

    public Account modificarCuenta(Long idCuenta, Account cuenta) {
        Account auxCuenta = accountRepository.getReferenceById(idCuenta);

        if (cuenta.getNumeroCuenta() != null) auxCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        if (cuenta.getMoneda() != null) auxCuenta.setMoneda(cuenta.getMoneda());
        if (cuenta.getSaldo() != null) auxCuenta.setSaldo(cuenta.getSaldo());
        if (cuenta.getActivo() != null) auxCuenta.setActivo(cuenta.getActivo());
        if (cuenta.getFechaModificacion() != null) auxCuenta.setFechaModificacion(cuenta.getFechaModificacion());

        return accountRepository.save(auxCuenta);
    }

    public void eliminarCuenta(Long idCuenta) {
        accountRepository.deleteById(idCuenta);
    }
}


