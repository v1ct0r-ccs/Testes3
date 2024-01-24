package org.anbruvic.dao.jpa;


import org.anbruvic.dao.generic.GenericJpaDB1DAO;
import org.anbruvic.domain.jpa.ClienteJpa;

public class ClienteJpaDAO extends GenericJpaDB1DAO<ClienteJpa, Long> implements IClienteJpaDAO<ClienteJpa> {

    public ClienteJpaDAO() {
        super(ClienteJpa.class);
    }
}
