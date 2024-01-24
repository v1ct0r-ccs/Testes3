package org.anbruvic.jpa;

import org.anbruvic.dao.jpa.ClienteJpaDAO;
import org.anbruvic.dao.jpa.ClienteJpaDB2DAO;
import org.anbruvic.dao.jpa.IClienteJpaDAO;
import org.anbruvic.domain.jpa.ClienteJpa;
import org.anbruvic.exceptions.DAOException;
import org.anbruvic.exceptions.MaisDeUmRegistroException;
import org.anbruvic.exceptions.TableException;
import org.anbruvic.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.*;

public class ClienteJpaDAO2BancoTest {
    private IClienteJpaDAO<ClienteJpa> clienteDAO;

    private IClienteJpaDAO<ClienteJpa> clienteDB2Dao;

    private Random rd;

    public ClienteJpaDAO2BancoTest() {
        this.clienteDAO = new ClienteJpaDAO();
        this.clienteDB2Dao = new ClienteJpaDB2DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<ClienteJpa> list1 = clienteDAO.buscarTodos();
        excluir1(list1);

        Collection<ClienteJpa> list2 = clienteDB2Dao.buscarTodos();
        excluir2(list2);
    }

    private void excluir1(Collection<ClienteJpa> list) {
        list.forEach(cli -> {
            try {
                clienteDAO.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluir2(Collection<ClienteJpa> list) {
        list.forEach(cli -> {
            try {
                clienteDB2Dao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
        ClienteJpa cliente = criarCliente();
        clienteDAO.cadastrar(cliente);

        ClienteJpa clienteConsultado = clienteDAO.consultar(cliente.getId());
        assertNotNull(clienteConsultado);

        cliente.setId(null);
        clienteDB2Dao.cadastrar(cliente);

        ClienteJpa clienteConsultado2 = clienteDB2Dao.consultar(cliente.getId());
        assertNotNull(clienteConsultado2);

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDAO.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa clienteConsultado = clienteDAO.consultar(retorno.getId());
        assertNotNull(clienteConsultado);

        clienteDAO.excluir(cliente);

        ClienteJpa clienteConsultado1 = clienteDAO.consultar(retorno.getId());
        assertNull(clienteConsultado1);
    }

    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDAO.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa clienteConsultado = clienteDAO.consultar(cliente.getId());
        assertNotNull(clienteConsultado);

        clienteDAO.excluir(cliente);
        clienteConsultado = clienteDAO.consultar(cliente.getId());
        assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDAO.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa clienteConsultado = clienteDAO.consultar(cliente.getId());
        assertNotNull(clienteConsultado);

        clienteConsultado.setNome("Victor Bruno");
        clienteDAO.alterar(clienteConsultado);

        ClienteJpa clienteAlterado = clienteDAO.consultar(clienteConsultado.getId());
        assertNotNull(clienteAlterado);
        assertEquals("Victor Bruno", clienteAlterado.getNome());

        clienteDAO.excluir(cliente);
        clienteConsultado = clienteDAO.consultar(clienteAlterado.getId());
        assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        ClienteJpa cliente = criarCliente();
        ClienteJpa retorno = clienteDAO.cadastrar(cliente);
        assertNotNull(retorno);

        ClienteJpa cliente1 = criarCliente();
        ClienteJpa retorno1 = clienteDAO.cadastrar(cliente1);
        assertNotNull(retorno1);

        Collection<ClienteJpa> list = clienteDAO.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDAO.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<ClienteJpa> list1 = clienteDAO.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

    private ClienteJpa criarCliente() {
        ClienteJpa cliente = new ClienteJpa();
        cliente.setCpf(rd.nextLong());
        cliente.setNome("Victor");
        cliente.setCidade("São Paulo");
        cliente.setEnd("End");
        cliente.setEstado("SP");
        cliente.setNumero(10);
        cliente.setTel(1199999999L);
        return cliente;
    }


}
