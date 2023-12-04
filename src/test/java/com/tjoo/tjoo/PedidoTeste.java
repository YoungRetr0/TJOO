package com.tjoo.tjoo;

import com.tjoo.tjoo.domain.*;
import com.tjoo.tjoo.service.PedidoService;
import com.tjoo.tjoo.service.ProdutoService;
import com.tjoo.tjoo.service.SolicitanteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PedidoTeste {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void testeCriarPedidos() {
        Solicitante solicitante = new Solicitante("João", "123.456.789-00", "joao@gmail.com");

        Pedido pedido1 = new PedidoImpl("Pedido 1", LocalDateTime.now(), 1.2f, solicitante);
        Pedido pedido2 = new PedidoImpl("Pedido 2", LocalDateTime.now(), 2.2f, solicitante);
        Pedido pedido3 = new PedidoImpl("Pedido 3", LocalDateTime.now(), 3.2f, solicitante);
        Bebida bebida = new Bebida("Coca-Cola", 6.5f, 1, true, 500.0f, "Coca-Cola Company");
        Comida comida = new Comida("Pizza Margherita", 25.0f, 2, 800, false, "Molho de tomate, mussarela, manjericão");
        Sobremesa sobremesa = new Sobremesa("Bolo de Chocolate", 15.0f, 3, 1.5f, true, "Delicioso bolo de chocolate");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(bebida);
        produtos.add(comida);
        produtos.add(sobremesa);

        pedido1.setProdutos(produtos);
        pedido2.setProdutos(produtos);
        pedido3.setProdutos(produtos);

        assertNotNull(pedido1);
        assertNotNull(pedido2);
        assertNotNull(pedido3);
    }

    @Test
    @Transactional
    @Rollback
    public void testeSalvarPedido() {
        Solicitante solicitante = new Solicitante("João", "123.456.789-00", "joao@gmail.com");
        solicitanteService.salvar(solicitante);

        Pedido pedido = new PedidoImpl("Novo Pedido", LocalDateTime.now(), 4.2f, solicitante);
        Bebida bebida = new Bebida("Coca-Cola", 6.5f, 1, true, 500.0f, "Coca-Cola Company");
        Comida comida = new Comida("Pizza Margherita", 25.0f, 2, 800, false, "Molho de tomate, mussarela, manjericão");
        Sobremesa sobremesa = new Sobremesa("Bolo de Chocolate", 15.0f, 3, 1.5f, true, "Delicioso bolo de chocolate");

        List<Produto> produtos = new ArrayList<>();
        produtos.add(bebida);
        produtos.add(comida);
        produtos.add(sobremesa);

        pedido.setProdutos(produtos);

        produtoService.saveAll(produtos);

        Pedido pedidoSalvo = pedidoService.salvar(pedido);

        assertNotNull(pedidoSalvo);
        assertEquals("Novo Pedido", pedidoSalvo.getDescricao());
    }

    @Test
    @Transactional
    @Rollback
    public void testeListarPedidos() {
        // Limpeza do banco de dados ou anotações @Transactional e @Rollback

        Solicitante solicitante = new Solicitante("João", "123.456.789-00", "joao@gmail.com");
        solicitanteService.salvar(solicitante);

        Bebida bebida = new Bebida("Coca-Cola", 6.5f, 1, true, 500.0f, "Coca-Cola Company");
        Comida comida = new Comida("Pizza Margherita", 25.0f, 2, 800, false, "Molho de tomate, mussarela, manjericão");
        Sobremesa sobremesa = new Sobremesa("Bolo de Chocolate", 15.0f, 3, 1.5f, true, "Delicioso bolo de chocolate");

        List<Produto> produtos = Arrays.asList(bebida, comida, sobremesa);
        produtoService.saveAll(produtos);

        Pedido pedido1 = new PedidoImpl("Pedido 1", LocalDateTime.now(), 1.2f, solicitante);
        Pedido pedido2 = new PedidoImpl("Pedido 2", LocalDateTime.now(), 2.2f, solicitante);
        Pedido pedido3 = new PedidoImpl("Pedido 3", LocalDateTime.now(), 3.2f, solicitante);

        pedido1.setProdutos(produtos);
        pedido2.setProdutos(produtos);
        pedido3.setProdutos(produtos);

        pedidoService.salvar(pedido1);
        pedidoService.salvar(pedido2);
        pedidoService.salvar(pedido3);

        List<Pedido> pedidos = pedidoService.listarTodos();

        // Acesse explicitamente os produtos para evitar problemas de lazy loading
        pedidos.forEach(pedido -> pedido.getProdutos().size());

        assertEquals(3, pedidos.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testeAdicionarProdutos() {
        Solicitante solicitante = new Solicitante("João", "123.456.789-00", "joao@gmail.com");
        solicitanteService.salvar(solicitante);

        Bebida bebida = new Bebida("Coca-Cola", 6.5f, 1, true, 500.0f, "Coca-Cola Company");
        Comida comida = new Comida("Pizza Margherita", 25.0f, 2, 800, false, "Molho de tomate, mussarela, manjericão");
        Sobremesa sobremesa = new Sobremesa("Bolo de Chocolate", 15.0f, 3, 1.5f, true, "Delicioso bolo de chocolate");
        float total = bebida.getValor() + comida.getValor() + sobremesa.getValor();

        Pedido pedido = new PedidoImpl("Pedido 1", LocalDateTime.now(), total, solicitante);;

        List<Produto> produtos = new ArrayList<>();
        produtos.add(bebida);
        produtos.add(comida);
        produtos.add(sobremesa);

        pedido.setProdutos(produtos);
        produtoService.saveAll(produtos);

        assertEquals(3, pedido.getProdutos().size());
    }
}
