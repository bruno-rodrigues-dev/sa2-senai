package com.sa2.controller;

import com.sa2.model.Lanche;
import com.sa2.service.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lanches")
public class LancheController {

    @Autowired
    private LancheService lancheService;

    @GetMapping
    public ResponseEntity<List<Lanche>> listarTodos() {
        return ResponseEntity.ok(lancheService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lanche> buscarPorId(@PathVariable Long id) {
        return lancheService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lanche> salvar(@RequestBody Lanche lanche) {
        Lanche novoLanche = lancheService.salvar(lanche);
        return ResponseEntity.status(201).body(novoLanche);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lanche> atualizar(@PathVariable Long id, @RequestBody Lanche lanche) {
        try {
            Lanche lancheAtualizado = lancheService.atualizar(id, lanche);
            return ResponseEntity.ok(lancheAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            lancheService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
