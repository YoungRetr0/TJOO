package com.tjoo.tjoo;

import com.tjoo.tjoo.domain.Sobremesa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SobremesaTeste {

    @Test
    public void testeCriarSobremesa() {
        Sobremesa sobremesa = new Sobremesa("Bolo de Chocolate", 15.0f, 3, 1.5f, true, "Delicioso bolo de chocolate");

        assertNotNull(sobremesa);
        assertEquals("Bolo de Chocolate", sobremesa.getNome());
        assertEquals(15.0f, sobremesa.getValor(), 0.0f);
        assertEquals(3, sobremesa.getCodigo());
        assertEquals(1.5f, sobremesa.getQuantidade(), 0.0f);
        assertTrue(sobremesa.isDoce());
        assertEquals("Delicioso bolo de chocolate", sobremesa.getInformacao());
    }
}