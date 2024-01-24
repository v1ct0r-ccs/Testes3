package org.anbruvic.domain.jpa;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTO_QUANTIDADE")
public class ProdutoQuantidadeJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_qtd_seq")
    @SequenceGenerator(name = "prod:qtd_seq", sequenceName = "sq_prod_qtd", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProdutoJpa produto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quatidade;

    @Column(name = "VALOR_TOTAL", nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda_fk",
                foreignKey = @ForeignKey(name = "fk_prod_qtd_venda"),
                referencedColumnName = "id", nullable = false
    )
    private VendaJpa venda;


    public ProdutoQuantidadeJpa() {
        this.quatidade = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoJpa getProduto() {
        return produto;
    }

    public void setProduto(ProdutoJpa produto) {
        this.produto = produto;
    }

    public Integer getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(Integer quatidade) {
        this.quatidade = quatidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public VendaJpa getVenda() {
        return venda;
    }

    public void setVenda(VendaJpa venda) {
        this.venda = venda;
    }

    public void adicionar(Integer quatidade) {
        this.quatidade += quatidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quatidade));
        BigDecimal novoTotal = this.valorTotal.add(novoValor);
        this.valorTotal = novoTotal;
    }

    public void remover(Integer quatidade) {
        this.quatidade -= quatidade;
        BigDecimal novoValor = this.produto.getValor().multiply(BigDecimal.valueOf(quatidade));
        this.valorTotal = this.valorTotal.subtract(novoValor);

    }
}
