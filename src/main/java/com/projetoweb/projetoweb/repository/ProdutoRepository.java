package com.projetoweb.projetoweb.repository;

import com.projetoweb.projetoweb.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "SELECT * from produtos where lower(categoria) LIKE CONCAT('%', lower(?1), '%')"+
        "OR lower(marca) LIKE CONCAT('%', lower(?1), '%')"+
        "OR lower(modelo) LIKE CONCAT('%', lower(?1), '%')"+
        "OR lower(cor) LIKE CONCAT('%', lower(?1), '%')"
        , nativeQuery = true)
    List<Produto> findByKeyword(String keyword);
}
