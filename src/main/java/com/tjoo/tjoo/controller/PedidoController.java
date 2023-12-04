package com.tjoo.tjoo.controller;

import com.tjoo.tjoo.domain.Pedido;
import com.tjoo.tjoo.domain.PedidoImpl;
import com.tjoo.tjoo.domain.Solicitante;
import com.tjoo.tjoo.service.PedidoService;
import com.tjoo.tjoo.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private SolicitanteService solicitanteService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoImpl pedidoImpl) {
        Solicitante solicitanteExistente = solicitanteService.buscarPorCpf(pedidoImpl.getSolicitante().getCpf());

        if (solicitanteExistente != null) {
            pedidoImpl.setSolicitante(solicitanteExistente);
        } else {
            Solicitante novoSolicitante = solicitanteService.salvar(pedidoImpl.getSolicitante());
            pedidoImpl.setSolicitante(novoSolicitante);
        }

        Pedido pedidoSalvo = pedidoService.salvar(pedidoImpl);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
