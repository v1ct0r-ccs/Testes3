package org.anbruvic.dao.jpa;


import jakarta.persistence.Persistence;
import org.anbruvic.dao.generic.IGenericJpaDAO;
import org.anbruvic.domain.jpa.Persistente;

public interface IClienteJpaDAO<T extends Persistence & Persistente> extends IGenericJpaDAO<T, Long> {

}
