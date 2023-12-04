package com.tjoo.tjoo;

import com.tjoo.tjoo.domain.Comida;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ComidaTeste {

    @Test
    public void testeCriarComida() {
        Comida comida = new Comida("Pizza Margherita", 25.0f, 2, 800.0f, false, "Molho de tomate, mussarela, manjericão");

        assertNotNull(comida);
        assertEquals("Pizza Margherita", comida.getNome());
        assertEquals(25.0f, comida.getValor(), 0.0f);
        assertEquals(2, comida.getCodigo());
        assertEquals(800.0f, comida.getPeso(), 0.0f);
        assertFalse(comida.isVegano());
        assertEquals("Molho de tomate, mussarela, manjericão", comida.getIngredientes());
    }

}
