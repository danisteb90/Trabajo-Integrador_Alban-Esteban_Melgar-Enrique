package com.dh.Clase15_SpringMVC.dao;

import java.util.List;

public interface IDAO<T> {
    //crud

    //crear
    T guardar (T t);

    //consultar todos los T
    List<T> listarTodos();

    //consultar por id
    T consultarPorId(Integer id);

    //eliminar
    boolean eliminarPorId(Integer id);

    //actualizar
    T actualizar(T t);
}
