package com.willian.loja.Repository;

import com.willian.loja.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional
    @Query(value = "select * from produto where categoria_id = ?1 order by nome",
            nativeQuery = true)
    List<Produto> findByCategoria(Integer id);

}
