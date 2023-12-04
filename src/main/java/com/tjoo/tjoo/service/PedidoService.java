package com.tjoo.tjoo.service;

import com.tjoo.tjoo.domain.Pedido;
import com.tjoo.tjoo.exception.PedidoInvalidoException;
import com.tjoo.tjoo.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService implements CrudService<Pedido> {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido salvar(Pedido pedido) {
        validarPedido(pedido);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    private void validarPedido(Pedido pedido) {
        if (pedido.getProdutos().size() > 10) {
            throw new PedidoInvalidoException("Número máximo de produtos por pedido excedido.");
        }
    }

}
