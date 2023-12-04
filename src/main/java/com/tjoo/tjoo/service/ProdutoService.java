package com.tjoo.tjoo.service;

import com.tjoo.tjoo.domain.Produto;
import com.tjoo.tjoo.exception.ProdutoInvalidoException;
import com.tjoo.tjoo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService implements CrudService<Produto> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(Produto produto) {
        verificaValorProduto(produto.getValor());
        return produtoRepository.save(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    private void verificaValorProduto(float valor) {
        if (valor > 1000) {
            throw new ProdutoInvalidoException("O valor do produto não pode ser maior que 1000.");
        }
    }

    public void saveAll(List<Produto> produtos) {
        produtoRepository.saveAll(produtos);
    }

}
