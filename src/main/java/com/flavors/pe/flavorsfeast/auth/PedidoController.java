package com.flavors.pe.flavorsfeast.auth;

import com.flavors.pe.flavorsfeast.dto.RegistarPedidoDto;
import com.flavors.pe.flavorsfeast.services.impl.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/v2/")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("pedido")
    public ResponseEntity<?> registrarPedido(@RequestBody RegistarPedidoDto registarPedidoDto) {
        pedidoService.registrarPedido(registarPedidoDto);
        Map<String, String> response = Map.of("mensaje", "Pedido realizado exitosamente!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("pedidos")
    public ResponseEntity<?> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}
