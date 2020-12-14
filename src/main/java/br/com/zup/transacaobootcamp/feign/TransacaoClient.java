package br.com.zup.transacaobootcamp.feign;

import br.com.zup.transacaobootcamp.dto.request.CartaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "transacoes", url = "${host.transacoes}")
public interface TransacaoClient {

    @PostMapping("/api/cartoes")
    void iniciaTransacao(@RequestBody CartaoRequest cartaoRequest);

    @DeleteMapping("/api/cartoes/{id}")
    void deletaTransacao(@PathVariable("id") String idCartao);


}
