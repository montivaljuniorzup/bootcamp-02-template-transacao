package br.com.zup.transacaobootcamp.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoRequest {

    @NotBlank
    private String id;

    @NotBlank
    @Email
    private String email;

    public CartaoRequest(@NotBlank String id, @NotBlank @Email String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
