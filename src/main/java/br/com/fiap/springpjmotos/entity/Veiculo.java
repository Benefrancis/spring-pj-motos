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
@Table(name = "TB_VEICULO")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_VEICULO")
    @SequenceGenerator(name = "SQ_VEICULO", sequenceName = "SQ_VEICULO", allocationSize = 1)
    @Column(name = "ID_VEICULO")
    private Long id;

    @Column(name = "NM_VEICULO")
    private String nome;

    private String cor;

    private Double preco;


    private Short cilindradas;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TIPO",
            referencedColumnName = "ID_TIPO_VEICULO",
            foreignKey = @ForeignKey(
                    name = "FK_TIPO_VEICULO"
            )
    )
    private TipoVeiculo tipo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_ACESSORIOS_VEICULO",
            joinColumns = {
                    @JoinColumn(
                            name = "VEICULO",
                            referencedColumnName = "ID_VEICULO",
                            foreignKey = @ForeignKey(
                                    name = "FK_VEICULO_ACESSORIO"
                            )

                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ACESSORIO",
                            referencedColumnName = "ID_ACESSORIO",
                            foreignKey = @ForeignKey(
                                    name = "FK_ACESSORIO_VEICULO"
                            )
                    )
            }
    )

    private Set<Acessorio> acessorios = new LinkedHashSet<>();

}
