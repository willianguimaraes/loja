package com.willian.loja.Repository;

import com.willian.loja.domain.Endereco;
import com.willian.loja.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {}
