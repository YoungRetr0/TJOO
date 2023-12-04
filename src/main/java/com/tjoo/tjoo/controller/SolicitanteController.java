package com.tjoo.tjoo.controller;

import com.tjoo.tjoo.domain.Solicitante;
import com.tjoo.tjoo.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/solicitantes")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitanteService;

    @GetMapping("/{id}")
    public ResponseEntity<Solicitante> getSolicitante(@PathVariable Long id) {
        Solicitante solicitante = solicitanteService.buscarPorId(id);
        return ResponseEntity.ok(solicitante);
    }

    @GetMapping
    public ResponseEntity<List<Solicitante>> listarSolicitantes() {
        List<Solicitante> solicitantes = solicitanteService.listarTodos();
        return ResponseEntity.ok(solicitantes);
    }

    @PostMapping
    public ResponseEntity<Solicitante> criarSolicitante(@RequestBody Solicitante solicitante) {
        Solicitante solicitanteSalvo = solicitanteService.salvar(solicitante);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitanteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitante> atualizarSolicitante(@PathVariable Long id, @RequestBody Solicitante solicitante) {
        Solicitante solicitanteExistente = solicitanteService.buscarPorId(id);

        if (solicitanteExistente != null) {
            solicitanteExistente.setNome(solicitante.getNome());
            solicitanteExistente.setCpf(solicitante.getCpf());
            solicitanteExistente.setEmail(solicitante.getEmail());

            Solicitante solicitanteAtualizado = solicitanteService.salvar(solicitanteExistente);
            return ResponseEntity.ok(solicitanteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSolicitante(@PathVariable Long id) {
        solicitanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Solicitante> getSolicitantePorCpf(@PathVariable String cpf) {
        Solicitante solicitante = solicitanteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(solicitante);
    }

}
