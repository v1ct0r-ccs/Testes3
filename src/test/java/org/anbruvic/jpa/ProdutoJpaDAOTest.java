package org.anbruvic.jpa;

import org.anbruvic.dao.jpa.IProdutoJpaDAO;
import org.anbruvic.dao.jpa.ProdutoJpaDAO;
import org.anbruvic.domain.jpa.ProdutoJpa;
import org.anbruvic.exceptions.DAOException;
import org.anbruvic.exceptions.MaisDeUmRegistroException;
import org.anbruvic.exceptions.TableException;
import org.anbruvic.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.*;

public class ProdutoJpaDAOTest {

    private IProdutoJpaDAO produtoDAO;

    public ProdutoJpaDAOTest() {
        this.produtoDAO = new ProdutoJpaDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<ProdutoJpa> list = produtoDAO.buscarTodos();
        list.forEach(cli -> {
            try {
                produtoDAO.excluir(cli);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisar() throws MaisDeUmRegistroException, TableException, DAOException, TipoChaveNaoEncontradaException {
        ProdutoJpa produto = criarProduto("A1");
        assertNotNull(produto);
        ProdutoJpa produtoDB = this.produtoDAO.consultar(produto.getId());
        assertNotNull(produtoDB);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJpa produto = criarProduto("A2");
        assertNotNull(produto);
    }

    @Test
    public void excluir() throws DAOException, TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = criarProduto("A3");
        assertNotNull(produto);
        this.produtoDAO.excluir(produto);

        ProdutoJpa produtoBD = this.produtoDAO.consultar(produto.getId());
        assertNull(produtoBD);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        ProdutoJpa produto = criarProduto("A4");
        produto.setNome("Victor Bruno");
        produtoDAO.alterar(produto);

        ProdutoJpa produtoBD = this.produtoDAO.consultar(produto.getId());
        assertEquals("Victor Bruno", produtoBD.getNome());
    }

    @Test
    public void buscarTodos() throws DAOException, TipoChaveNaoEncontradaException {
        criarProduto("A5");
        criarProduto("A6");
        Collection<ProdutoJpa> list = produtoDAO.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        for (ProdutoJpa prod : list) {
            this.produtoDAO.excluir(prod);
        }

        list = produtoDAO.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 0);
    }

    private ProdutoJpa criarProduto(String codigo) throws TipoChaveNaoEncontradaException, DAOException {
        ProdutoJpa produto = new ProdutoJpa();
        produto.setCodigo(codigo);
        produto.setNome("Produto 1");
        produto.setDescricao("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produtoDAO.cadastrar(produto);
        return produto;
    }
}
