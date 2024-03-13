package br.com.fiap.springpjmotos.resources;

import br.com.fiap.springpjmotos.entity.Acessorio;
import br.com.fiap.springpjmotos.entity.TipoVeiculo;
import br.com.fiap.springpjmotos.repository.AcessorioRepository;
import br.com.fiap.springpjmotos.repository.TipoVeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/acessorio")
public class AcessorioResource {

    @Autowired
    private AcessorioRepository repo;

    @GetMapping
    public List<Acessorio> findAll(){
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Acessorio findById(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Acessorio save(@RequestBody Acessorio acessorio){
        return repo.save(acessorio);
    }

}
