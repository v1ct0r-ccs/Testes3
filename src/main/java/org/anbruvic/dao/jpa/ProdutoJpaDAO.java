package org.anbruvic.dao.jpa;


import org.anbruvic.dao.generic.GenericJpaDB1DAO;
import org.anbruvic.domain.jpa.ProdutoJpa;

public class ProdutoJpaDAO extends GenericJpaDB1DAO<ProdutoJpa, Long> implements IProdutoJpaDAO {

    public ProdutoJpaDAO() {
        super(ProdutoJpa.class);
    }
}
