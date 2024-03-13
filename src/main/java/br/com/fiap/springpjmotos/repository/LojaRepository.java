package br.com.fiap.springpjmotos.repository;

import br.com.fiap.springpjmotos.entity.Acessorio;
import br.com.fiap.springpjmotos.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

}
