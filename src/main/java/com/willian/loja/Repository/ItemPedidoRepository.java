package com.willian.loja.Repository;

import com.willian.loja.domain.ItemPedido;
import com.willian.loja.domain.ItemPedidoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {
}
