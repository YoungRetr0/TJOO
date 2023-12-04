package com.tjoo.tjoo;

import com.tjoo.tjoo.domain.Solicitante;
import com.tjoo.tjoo.service.SolicitanteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SolicitanteTeste {

    @Autowired
    private SolicitanteService solicitanteService;

    @Test
    public void testeCriarSolicitante() {
        Solicitante solicitante = new Solicitante("João", "123456789", "joao@gmail.com");

        assertNotNull(solicitante);
        assertEquals("João", solicitante.getNome());
        assertEquals("123456789", solicitante.getCpf());
        assertEquals("joao@gmail.com", solicitante.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void testeSalvarSolicitante() {
        Solicitante solicitante = new Solicitante("maria", "123.456.789-00", "maria@gmail.com");
        Solicitante solicitanteSalvo = solicitanteService.salvar(solicitante);

        assertNotNull(solicitanteSalvo);
        assertEquals("maria", solicitanteSalvo.getNome());
        assertEquals("123.456.789-00", solicitanteSalvo.getCpf());
        assertEquals("maria@gmail.com", solicitanteSalvo.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void testeListarSolicitantes() {
        Solicitante solicitante1 = new Solicitante("joao", "123.456.789-00", "joao@gmail.com");
        Solicitante solicitante2 = new Solicitante("maria", "123.456.789-00", "maria@gmail.com");
        Solicitante solicitante3 = new Solicitante("jose", "123.456.789-00", "jose@gmail.com");

        solicitanteService.salvar(solicitante1);
        solicitanteService.salvar(solicitante2);
        solicitanteService.salvar(solicitante3);

        assertEquals(3, solicitanteService.listarTodos().size());
    }

    @Test
    @Transactional
    @Rollback
    public void testeAtualizarSolicitante() {
        Solicitante solicitante = new Solicitante("Maria", "123.456.789-00", "maria@gmail.com");
        solicitanteService.salvar(solicitante);

        solicitante.setNome("Maria Madalena");
        solicitante.setEmail("maria.madalena@gmail.com");

        Solicitante solicitanteAtualizado = solicitanteService.salvar(solicitante);

        assertNotNull(solicitanteAtualizado);
        assertEquals("Maria Madalena", solicitanteAtualizado.getNome());
        assertEquals("maria.madalena@gmail.com", solicitanteAtualizado.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void testeDeletarSolicitante() {
        Solicitante solicitante = new Solicitante("jose", "123.456.789-00", "jose@gmail.com");
        solicitanteService.salvar(solicitante);

        solicitanteService.deletar(solicitante.getId());

        assertEquals(0, solicitanteService.listarTodos().size());
    }
}
