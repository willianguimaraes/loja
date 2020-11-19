package com.willian.loja.domain;

import com.willian.loja.enums.EstadoPagamento;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract  class Pagamento implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pagamento;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento(){

    }

    public Pagamento(Integer id, EstadoPagamento pagamento, Pedido pedido) {
        this.id = id;
        this.pagamento = pagamento.getCod();
        this.pedido = pedido;
    }

    public EstadoPagamento getEstadoPagamento(){
        return EstadoPagamento.toEnum(pagamento);
    }

    public void setPagamento(EstadoPagamento pagamento){
        this.pagamento = pagamento.getCod();
    }

}
