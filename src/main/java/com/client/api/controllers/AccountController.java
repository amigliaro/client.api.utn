package com.client.api.controllers;

import com.client.api.models.Account;
import com.client.api.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> listarCuentas() {
        return accountService.listarCuentas();
    }

    @GetMapping("/{idCuenta}")
    public Account getCuentaById(@PathVariable Long idCuenta) {
        return accountService.getCuentaById(idCuenta);
    }

    @PostMapping
    public Account insertarCuenta(@RequestBody Account cuenta) {
        return accountService.insertarCuenta(cuenta);
    }

    @PutMapping("/{idCuenta}")
    public Account modificarCuenta(@PathVariable Long idCuenta, @RequestBody Account cuenta) {
        return accountService.modificarCuenta(idCuenta, cuenta);
    }

    @DeleteMapping("/{idCuenta}")
    public void eliminarCuenta(@PathVariable Long idCuenta) {
        accountService.eliminarCuenta(idCuenta);
    }
}
