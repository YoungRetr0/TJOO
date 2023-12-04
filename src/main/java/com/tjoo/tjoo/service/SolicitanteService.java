package com.tjoo.tjoo.service;

import com.tjoo.tjoo.domain.Solicitante;
import com.tjoo.tjoo.exception.SolicitanteInvalidoException;
import com.tjoo.tjoo.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SolicitanteService implements CrudService<Solicitante> {

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Override
    public Solicitante salvar(Solicitante solicitante) {
        validarCpf(solicitante.getCpf());
        return solicitanteRepository.save(solicitante);
    }

    @Override
    public Solicitante buscarPorId(Long id) {
        return solicitanteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Solicitante> listarTodos() {
        return solicitanteRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        solicitanteRepository.deleteById(id);
    }

    public Solicitante buscarPorCpf(String cpf) {
        validarCpf(cpf);
        return solicitanteRepository.findByCpf(cpf.replaceAll("[^0-9]", ""));
    }

    private void validarCpf(String cpf) {
        if (!cpfValido(cpf)) {
            throw new SolicitanteInvalidoException("CPF inválido: " + cpf);
        }
    }

    private boolean cpfValido(String cpf) {
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");

        return cpfNumerico.length() == 11;
    }

}
