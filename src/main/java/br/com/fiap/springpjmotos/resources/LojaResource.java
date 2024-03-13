package br.com.fiap.springpjmotos.resources;

import br.com.fiap.springpjmotos.entity.Acessorio;
import br.com.fiap.springpjmotos.entity.Loja;
import br.com.fiap.springpjmotos.repository.AcessorioRepository;
import br.com.fiap.springpjmotos.repository.LojaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/loja")
public class LojaResource {

    @Autowired
    private LojaRepository repo;

    @GetMapping
    public List<Loja> findAll(){
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Loja findById(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Loja save(@RequestBody Loja loja){
        return repo.save(loja);
    }

}
