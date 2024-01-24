package org.anbruvic.dao.generic;

import org.anbruvic.domain.jpa.Persistente;

import java.io.Serializable;

/**
 * @author rodrigo.pires
 *
 */
public abstract class GenericJpaDB3DAO <T extends Persistente, E extends Serializable>
        extends GenericJpaDAO<T,E> {

    public GenericJpaDB3DAO(Class<T> persistenteClass) {
        super(persistenteClass, "Mysql");
    }

}
