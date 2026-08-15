package com.client.api.services;

import com.client.api.clients.DolarClient;
import com.client.api.dto.AccountExtended;
import com.client.api.exceptions.InternalServerErrorException;
import com.client.api.exceptions.NotFoundException;
import com.client.api.mappers.AccountMapper;
import com.client.api.models.Account;
import com.client.api.models.Client;
import com.client.api.repositories.AccountRepository;
import com.client.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final DolarClient dolarClient;

    public AccountService(AccountRepository accountrepository, ClientRepository clientRepository, DolarClient dolarClient) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountrepository;
        this.dolarClient = dolarClient;
    }

    public List<AccountExtended> listarCuentas() {
        try {
            return AccountMapper.AccountToDTOList(accountRepository.findAll());
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al listar las cuentas: " + ex.getMessage());
        }
    }

    public AccountExtended getCuentaById(Long id) {

        if (accountRepository.findById(id).isPresent()) {
            return AccountMapper.AccounttoDTO(accountRepository.findById(id).get());

        } else {
            throw new NotFoundException("No se encontró la cuenta solicitada.");
        }
    }

    public AccountExtended insertarCuenta(Long idCliente, AccountExtended cuentaDTO) {

        Optional<Client> client = (clientRepository.findById(idCliente));


        if (client.isPresent()) {
         Account cuenta = Account.builder()
                 .numeroCuenta(cuentaDTO.getNumeroCuenta())
                 .moneda(cuentaDTO.getMoneda())
                 .client(client.get())
                 .activo(true)
                 .saldo(0.0)
                 .fechaCreacion(LocalDateTime.now())
                 .fechaModificacion(LocalDateTime.now())
                 .build();

            return AccountMapper.AccounttoDTO(accountRepository.save(cuenta));
        } else {
            throw new NotFoundException("No se encontró el cliente ingresado para la creación de la cuenta.");
        }
    }

    public AccountExtended modificarCuenta(Long idCuenta, AccountExtended cuenta) {
        Optional<Account> auxCuenta = accountRepository.findById(idCuenta);

        if (auxCuenta.isPresent()) {
            if (cuenta.getNumeroCuenta() != null) auxCuenta.get().setNumeroCuenta(cuenta.getNumeroCuenta());
            if (cuenta.getMoneda() != null) auxCuenta.get().setMoneda(cuenta.getMoneda());
            if (cuenta.getSaldo() != null) auxCuenta.get().setSaldo(cuenta.getSaldo());
            if (cuenta.getFechaModificacion() != null)
                auxCuenta.get().setFechaModificacion(cuenta.getFechaModificacion());


        } else {
            throw new NotFoundException("No se encontró la cuenta para el id ingresado.");
        }
        try {
            return AccountMapper.AccounttoDTO(accountRepository.save(auxCuenta.get()));
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al modificar una cuenta: " + ex.getMessage());
        }
    }

    public void eliminarCuenta(Long idCuenta) {
        Optional<Account> auxCuenta = accountRepository.findById(idCuenta);

        if (auxCuenta.isPresent()) {
            try {
                accountRepository.deleteById(idCuenta);
            } catch (InternalServerErrorException ex) {
                throw new InternalServerErrorException("Error al eliminar una cuenta: " + ex.getMessage());
            }
        } else {
            throw new NotFoundException("No se encontró la cuenta para el id ingresado.");
        }

    }
}


