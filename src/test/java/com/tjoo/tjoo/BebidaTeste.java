package com.tjoo.tjoo;

import com.tjoo.tjoo.domain.Bebida;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BebidaTeste {

    @Test
    public void testeCriarBebida() {
        Bebida bebida = new Bebida("Coca-Cola", 3.0f, 1, true, 500.0f, "Coca-Cola");

        assertNotNull(bebida);
        assertEquals("Coca-Cola", bebida.getNome());
        assertEquals(3.0f, bebida.getValor(), 0.0f);
        assertEquals(1, bebida.getCodigo());
        assertTrue(bebida.isGelada());
        assertEquals(500.0f, bebida.getTamanho(), 0.0f);
        assertEquals("Coca-Cola", bebida.getMarca());
    }

}
