package org.olmedo.webapp.jsf3.services;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.olmedo.webapp.jsf3.entities.Categoria;
import org.olmedo.webapp.jsf3.entities.Producto;
import org.olmedo.webapp.jsf3.repositories.CrudRepository;
import org.olmedo.webapp.jsf3.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"}) // declaramos los roles
public class ProductoServiceImpl implements ProductoService {
    @Inject
    private ProductoRepository repository;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    @PermitAll // para que sea publico que todos puedan acceder
    public List<Producto> listar() {
        return repository.listar();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public Optional<Producto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @RolesAllowed({"ADMIN"}) // solamente para admin
    @Override
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @RolesAllowed({"USER","ADMIN"})
    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.ofNullable(categoriaRepository.porId(id));
    }

    // 7 implementamos el  metodo ahora vamos a ProductoController
    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}
