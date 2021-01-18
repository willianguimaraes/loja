package com.willian.loja.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.willian.loja.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PagamentoComBoleto extends Pagamento{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, Date dataVencimento) {
        super(id,estado,pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }
}
