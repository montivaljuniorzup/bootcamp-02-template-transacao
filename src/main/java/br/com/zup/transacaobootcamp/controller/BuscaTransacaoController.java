package br.com.zup.transacaobootcamp.controller;

import br.com.zup.transacaobootcamp.model.Transacao;
import br.com.zup.transacaobootcamp.repository.TransacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;


@RestController
@RequestMapping("/transacoes")
public class BuscaTransacaoController {

    private TransacaoRepository repository;

    public BuscaTransacaoController(TransacaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscaTodas(@PathVariable("id") String id, @PageableDefault(size = 10) Pageable pageable) {
        boolean existsByCartaoId = repository.existsByCartaoId(id);
        if(!existsByCartaoId){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
        }
        Optional<Page<Transacao>> cartoesOp = repository.findByCartaoId(id, pageable);
            return ResponseEntity.ok(cartoesOp);
    }
}
