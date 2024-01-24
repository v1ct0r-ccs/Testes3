package org.anbruvic.jpa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ClienteJpaDAOTest.class, ClienteJpaDAO2BancoTest.class, ClienteJpaDAO3BancoTest.class, ProdutoJpaDAOTest.class, VendaJpaDAOTest.class})
public class AllTests {

}
