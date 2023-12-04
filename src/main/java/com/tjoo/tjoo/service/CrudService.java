package com.tjoo.tjoo.service;

import java.util.List;

public interface CrudService<T> {
    T salvar(T objeto);

    T buscarPorId(Long id);

    List<T> listarTodos();

    void deletar(Long id);

}
