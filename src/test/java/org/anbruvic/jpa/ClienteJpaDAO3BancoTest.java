package org.anbruvic.jpa;

import org.anbruvic.dao.jpa.ClienteJpaDAO;
import org.anbruvic.dao.jpa.ClienteJpaDB2DAO;
import org.anbruvic.dao.jpa.ClienteJpaDB3DAO;
import org.anbruvic.dao.jpa.IClienteJpaDAO;
import org.anbruvic.domain.jpa.ClienteJpa;
import org.anbruvic.domain.jpa.ClienteJpa2;
import org.anbruvic.exceptions.DAOException;
import org.anbruvic.exceptions.MaisDeUmRegistroException;
import org.anbruvic.exceptions.TableException;
import org.anbruvic.exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Test;

import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.*;

public class ClienteJpaDAO3BancoTest {
    private IClienteJpaDAO<ClienteJpa> clienteDAO;

    private IClienteJpaDAO<ClienteJpa> clienteDB2DAO;

    private IClienteJpaDAO<ClienteJpa2> clienteDB3DAO;

    private Random rd;

    public ClienteJpaDAO3BancoTest() {
        this.clienteDAO = new ClienteJpaDAO();
        this.clienteDB2DAO = new ClienteJpaDB2DAO();
        this.clienteDB3DAO = new ClienteJpaDB3DAO();
        rd = new Random();
    }

    @After
    public void end() throws DAOException {
        Collection<ClienteJpa> list = clienteDAO.buscarTodos();
        excluir(list, clienteDAO);

        Collection<ClienteJpa> list2 = clienteDB2DAO.buscarTodos();
        excluir(list2, clienteDB2DAO);

        Collection<ClienteJpa2> list3 = clienteDB3DAO.buscarTodos();
        excluir3(list3);
    }

    private void excluir(Collection<ClienteJpa> list, IClienteJpaDAO<ClienteJpa> clienteDao) {
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void excluir3(Collection<ClienteJpa2> list) {
        list.forEach(cli -> {
            try {
                clienteDB3DAO.excluir(cli);
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
        clienteDB2DAO.cadastrar(cliente);

        ClienteJpa clienteConsultado2 = clienteDB2DAO.consultar(cliente.getId());
        assertNotNull(clienteConsultado2);

        ClienteJpa2 cliente2 = criarCliente2();
        clienteDB3DAO.cadastrar(cliente2);

        ClienteJpa2 clienteConsultado3 = clienteDB3DAO.consultar(cliente2.getId());
        assertNotNull(clienteConsultado3);

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

    private ClienteJpa2 criarCliente2() {
        ClienteJpa2 cliente = new ClienteJpa2();
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
