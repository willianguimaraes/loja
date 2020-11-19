package com.willian.loja.domain;

import com.willian.loja.enums.EstadoPagamento;
import lombok.*;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PagamentoComCartao extends Pagamento{

    private Integer numParcelas;


    public PagamentoComCartao(Integer id, EstadoPagamento pagamento, Pedido pedido, Integer numParcelas) {
        super(id, pagamento, pedido);
        this.numParcelas = numParcelas;
    }
}
