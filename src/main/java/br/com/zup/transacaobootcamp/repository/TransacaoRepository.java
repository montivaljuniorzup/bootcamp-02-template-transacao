package br.com.zup.transacaobootcamp.repository;

import br.com.zup.transacaobootcamp.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    Optional<Page<Transacao>> findByCartaoId(String id, Pageable pageable);

    boolean existsByCartaoId(String id);
}
