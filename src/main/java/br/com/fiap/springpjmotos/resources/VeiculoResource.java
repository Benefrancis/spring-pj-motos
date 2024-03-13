package br.com.fiap.springpjmotos.resources;

import br.com.fiap.springpjmotos.entity.Acessorio;
import br.com.fiap.springpjmotos.entity.Loja;
import br.com.fiap.springpjmotos.entity.TipoVeiculo;
import br.com.fiap.springpjmotos.entity.Veiculo;
import br.com.fiap.springpjmotos.repository.AcessorioRepository;
import br.com.fiap.springpjmotos.repository.LojaRepository;
import br.com.fiap.springpjmotos.repository.TipoVeiculoRepository;
import br.com.fiap.springpjmotos.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoResource {

    @Autowired
    private VeiculoRepository repo;

    @Autowired
    private TipoVeiculoRepository tipoVeiculoRepository;

    @Autowired
    private AcessorioRepository acessorioRepository;

    @GetMapping
    public List<Veiculo> findAll(){
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Veiculo findById(@PathVariable Long id){
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    @PostMapping
    public Veiculo save(@RequestBody Veiculo veiculo) {
        if (Objects.isNull(veiculo)) return null;

        if (Objects.nonNull(veiculo.getTipo().getId())){
            TipoVeiculo tipoVeiculo = tipoVeiculoRepository.findById(veiculo.getTipo().getId()).orElseThrow();
            veiculo.setTipo(tipoVeiculo);
        }

        return repo.save(veiculo);
    }

    @Transactional
    @PostMapping(value = "/{id}/acessorios")
    public Veiculo addAcessorio(@PathVariable Long id,@RequestBody Acessorio a){
        Veiculo veiculo = repo.findById(id).orElseThrow();
        if (Objects.isNull(a)) return null;

        if(Objects.nonNull(a.getId())){
            Acessorio acessorio = acessorioRepository.findById(a.getId()).orElseThrow();
            veiculo.getAcessorios().add(acessorio);
            return veiculo;
        }

        veiculo.getAcessorios().add(a);

        return veiculo;

    }

    @GetMapping(value = "/{id}/acessorios")
    public Set<Acessorio> findAcessorio(@PathVariable Long id){
        Veiculo veiculo = repo.findById(id).orElseThrow();

        return veiculo.getAcessorios();
    }
}
