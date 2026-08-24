package com.sa2.service;

import com.sa2.model.Lanche;
import com.sa2.repository.LancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancheService {

    @Autowired
    private LancheRepository lancheRepository;

    public List<Lanche> listarTodos() {
        return lancheRepository.findAll();
    }

    public Optional<Lanche> buscarPorId(Long id) {
        return lancheRepository.findById(id);
    }

    public Lanche salvar(Lanche lanche) {
        return lancheRepository.save(lanche);
    }

    public Lanche atualizar(Long id, Lanche lanche) {
        return lancheRepository.findById(id)
                .map(lancheExistente -> {
                    lancheExistente.setNome(lanche.getNome());
                    lancheExistente.setDescricao(lanche.getDescricao());
                    lancheExistente.setValor(lanche.getValor());
                    return lancheRepository.save(lancheExistente);
                })
                .orElseThrow(() -> new RuntimeException("Lanche não encontrado com o id: " + id));
    }

    public void deletar(Long id) {
        if (!lancheRepository.existsById(id)) {
            throw new RuntimeException("Lanche não encontrado com o id: " + id);
        }
        lancheRepository.deleteById(id);
    }

}
