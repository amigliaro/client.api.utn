package com.client.api.controllers;

import com.client.api.dto.AccountExtended;
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
    public List<AccountExtended> listarCuentas() {
        return accountService.listarCuentas();
    }

    @GetMapping("/{idCuenta}")
    public Account getCuentaById(@PathVariable Long idCuenta) {
        return accountService.getCuentaById(idCuenta);
    }

    @PostMapping("/{idCliente}")
    public Account insertarCuenta(@RequestBody Account cuenta, @PathVariable Long idCliente) {
        return accountService.insertarCuenta(idCliente, cuenta);
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
