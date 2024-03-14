package br.com.fiap.springpjmotos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "TB_LOJA")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_LOJA")
    @SequenceGenerator(name = "SQ_LOJA", sequenceName = "SQ_LOJA", allocationSize = 1)
    @Column(name = "ID_LOJA")
    private Long id;

    @Column(name = "NM_LOJA")
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_VEICULOS_COMERCIALIZADOS",
            joinColumns = {
                    @JoinColumn(
                            name = "LOJA",
                            referencedColumnName = "ID_LOJA",
                            foreignKey = @ForeignKey(
                                    name = "FK_LOJA_VEICULO"
                            )

                    )

            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "VEICULO",
                            referencedColumnName = "ID_VEICULO",
                            foreignKey = @ForeignKey(
                                    name = "FK_VEICULO_LOJA"
                            )
                    )

            }
    )

    private Set<Veiculo> veiculosComercializados = new LinkedHashSet<>();

}
