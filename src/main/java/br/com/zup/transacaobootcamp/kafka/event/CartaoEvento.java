package br.com.zup.transacaobootcamp.kafka.event;

import br.com.zup.transacaobootcamp.model.Cartao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CartaoEvento {

    @Id
    private String id;

    private String email;

    @Deprecated
    public CartaoEvento() {
    }

    public CartaoEvento(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public Cartao toModel() {
        return new Cartao(this.id, this.email);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
