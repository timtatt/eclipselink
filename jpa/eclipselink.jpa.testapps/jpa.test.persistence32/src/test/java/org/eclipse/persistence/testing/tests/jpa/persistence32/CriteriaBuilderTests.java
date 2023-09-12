package org.eclipse.persistence.testing.tests.jpa.persistence32;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.LocalDateField;
import jakarta.persistence.criteria.LocalTimeField;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;
import org.eclipse.persistence.jpa.JpaEntityManagerFactory;
import org.eclipse.persistence.testing.framework.jpa.junit.JUnitTestCase;
import org.eclipse.persistence.testing.models.jpa.persistence32.Persistence32TableCreator;
import org.eclipse.persistence.testing.models.jpa.persistence32.SyntaxEntity;
import org.junit.Assert;

public class CriteriaBuilderTests extends JUnitTestCase {

    // SyntaxEntity instances, array index is equal to ID
    // Values must be unique to get just single result and verify it by ID
    private static final SyntaxEntity[] ENTITIES = new SyntaxEntity[] {
            null, // Skip array index 0
            new SyntaxEntity(1L, "Left", null, null, null, null, null),
            new SyntaxEntity(2L, "right", null, null, null, null, null),
            new SyntaxEntity(3L, "LeftToken", "TokenRight", null, null, null, null),
            new SyntaxEntity(4L, null, null, null, LocalTime.of(10, 11, 12), null, null),
            new SyntaxEntity(5L, null, null, null, null, LocalDate.of(1918, 9, 28), null)
    };

    private JpaEntityManagerFactory emf = null;

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.setName("CriteriaBuilderTests");
        suite.addTest(new CriteriaBuilderTests("testSetup"));
        suite.addTest(new CriteriaBuilderTests("testAndPredicateAsListOf0"));
        suite.addTest(new CriteriaBuilderTests("testOrPredicateAsListOf0"));
        suite.addTest(new CriteriaBuilderTests("testAndPredicateAsListOf1"));
        suite.addTest(new CriteriaBuilderTests("testOrPredicateAsListOf1"));
        suite.addTest(new CriteriaBuilderTests("testAndPredicateAsListOf2"));
        suite.addTest(new CriteriaBuilderTests("testOrPredicateAsListOf2"));
        suite.addTest(new CriteriaBuilderTests("testAndPredicateAsListOfN"));
        suite.addTest(new CriteriaBuilderTests("testOrPredicateAsListOfN"));
        suite.addTest(new CriteriaBuilderTests("testLeftIntLen"));
        suite.addTest(new CriteriaBuilderTests("testLeftExprLen"));
        suite.addTest(new CriteriaBuilderTests("testRightIntLen"));
        suite.addTest(new CriteriaBuilderTests("testRightExprLen"));
        suite.addTest(new CriteriaBuilderTests("testReplaceExprExpr"));
        suite.addTest(new CriteriaBuilderTests("testReplaceExprStr"));
        suite.addTest(new CriteriaBuilderTests("testReplaceStrExpr"));
        suite.addTest(new CriteriaBuilderTests("testReplaceStrStr"));
        suite.addTest(new CriteriaBuilderTests("testExtractHourFromTime"));
        suite.addTest(new CriteriaBuilderTests("testExtractMinuteFromTime"));
        suite.addTest(new CriteriaBuilderTests("testExtractSecondFromTime"));
        suite.addTest(new CriteriaBuilderTests("testExtractYearFromDate"));
        suite.addTest(new CriteriaBuilderTests("testExtractMonthFromDate"));
        suite.addTest(new CriteriaBuilderTests("testExtractDayFromDate"));
        suite.addTest(new CriteriaBuilderTests("testExtractQuarterFromDate"));
        suite.addTest(new CriteriaBuilderTests("testExtractWeekFromDate"));
        return suite;
    }

    public CriteriaBuilderTests() {
    }

    public CriteriaBuilderTests(String name) {
        super(name);
        setPuName(getPersistenceUnitName());
    }

    @Override
    public String getPersistenceUnitName() {
        return "persistence32";
    }

    @Override
    public void setUp() {
        super.setUp();
        emf = getEntityManagerFactory(getPersistenceUnitName()).unwrap(EntityManagerFactoryImpl.class);
    }

    /**
     * The setup is done as a test, both to record its failure, and to allow
     * execution in the server.
     */
    public void testSetup() {
        new Persistence32TableCreator().replaceTables(JUnitTestCase.getServerSession(getPersistenceUnitName()));
        clearCache();
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                for (int i = 1; i < ENTITIES.length; i++) {
                    em.persist(ENTITIES[i]);
                }
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify case 0: in the implementation code.
    // JPA spec: This shall be always evaluated as true and all existing entities must be returned
    public void testAndPredicateAsListOf0() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                cQuery.where(cb.and(Collections.emptyList()));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(ENTITIES.length - 1, result.size());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify case 0: in the implementation code.
    // JPA spec: This shall be always evaluated as false and no entities must be returned
    public void testOrPredicateAsListOf0() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                cQuery.where(cb.or(Collections.emptyList()));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(0, result.size());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify case 1: in the implementation code
    public void testAndPredicateAsListOf1() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.and(List.of(
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam1"))
                )));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("strParam1", "LeftToken");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify case 1: in the implementation code
    public void testOrPredicateAsListOf1() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.or(List.of(
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam1"))
                )));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("strParam1", "LeftToken");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify case 2: in the implementation code
    public void testAndPredicateAsListOf2() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.and(List.of(
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam1")),
                        cb.equal(root.get("strVal2"), cb.parameter(String.class, "strParam2"))
                )));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("strParam1", "LeftToken");
                query.setParameter("strParam2", "TokenRight");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify case 2: in the implementation code
    public void testOrPredicateAsListOf2() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.or(List.of(
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam1")),
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam2"))
                )));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("strParam1", "Left");
                query.setParameter("strParam2", "right");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(2, result.size());
                assertFromSet(Set.of(1L, 2L), result.stream().map(SyntaxEntity::getId).toList());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify default: in the implementation code with list of size 4
    public void testAndPredicateAsListOfN() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.and(List.of(
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam1")),
                        cb.equal(root.get("strVal2"), cb.parameter(String.class, "strParam2")),
                        cb.isNull(root.get("intVal")),
                        cb.isNull(root.get("timeVal"))
                )));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("strParam1", "LeftToken");
                query.setParameter("strParam2", "TokenRight");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Verify default: in the implementation code with list of size 4
    public void testOrPredicateAsListOfN() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.or(List.of(
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam1")),
                        cb.equal(root.get("strVal1"), cb.parameter(String.class, "strParam2")),
                        cb.equal(root.get("timeVal"), cb.parameter(String.class, "timeParam")),
                        cb.equal(root.get("dateVal"), cb.parameter(String.class, "dateParam"))
                )));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("strParam1", "Left");
                query.setParameter("strParam2", "right");
                query.setParameter("timeParam", LocalTime.of(10, 11, 12));
                query.setParameter("dateParam", LocalDate.of(1918, 9, 28));
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(4, result.size());
                assertFromSet(Set.of(1L, 2L, 4L, 5L), result.stream().map(SyntaxEntity::getId).toList());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testLeftIntLen() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam1 = cb.parameter(String.class, "strParam1");
                cQuery.where(cb.equal(root.get("strVal1"), cb.left(strParam1, 4)));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam1, "Left substring to extract");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(1L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testLeftExprLen() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam1 = cb.parameter(String.class, "strParam1");
                cQuery.where(cb.equal(root.get("strVal1"), cb.left(strParam1, cb.literal(4))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam1, "Left substring to extract");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(1L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testRightIntLen() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam1 = cb.parameter(String.class, "strParam1");
                cQuery.where(cb.equal(root.get("strVal1"), cb.right(strParam1, 5)));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam1, "Extract substring from right");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(2L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testRightExprLen() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam1 = cb.parameter(String.class, "strParam1");
                cQuery.where(cb.equal(root.get("strVal1"), cb.right(strParam1, cb.literal(5))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam1, "Extract substring from right");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(2L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testReplaceExprExpr() {
        // Skip for Derby
        if (emf.getServerSession().getPlatform().isDerby()) {
            return;
        }
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam1 = cb.parameter(String.class, "strParam1");
                cQuery.where(cb.equal(root.get("strVal1"), cb.replace(strParam1, cb.literal("Unknown"), cb.literal("Left"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam1, "UnknownToken");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testReplaceExprStr() {
        // Skip for Derby
        if (emf.getServerSession().getPlatform().isDerby()) {
            return;
        }
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam2 = cb.parameter(String.class, "strParam2");
                cQuery.where(cb.equal(root.get("strVal2"), cb.replace(strParam2, cb.literal("Unknown"), "Right")));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam2, "TokenUnknown");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testReplaceStrExpr() {
        // Skip for Derby
        if (emf.getServerSession().getPlatform().isDerby()) {
            return;
        }
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam1 = cb.parameter(String.class, "strParam1");
                cQuery.where(cb.equal(root.get("strVal1"), cb.replace(strParam1, "Unknown", cb.literal("Left"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam1, "UnknownToken");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testReplaceStrStr() {
        // Skip for Derby
        if (emf.getServerSession().getPlatform().isDerby()) {
            return;
        }
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                ParameterExpression<String> strParam2 = cb.parameter(String.class, "strParam2");
                cQuery.where(cb.equal(root.get("strVal2"), cb.replace(strParam2, "Unknown", "Right")));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter(strParam2, "TokenUnknown");
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(3L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractHourFromTime() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "timeParam"),
                        cb.extract(LocalTimeField.HOUR, root.get("timeVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("timeParam", 10);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(4L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractMinuteFromTime() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "timeParam"),
                        cb.extract(LocalTimeField.MINUTE, root.get("timeVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("timeParam", 11);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(4L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractSecondFromTime() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "timeParam"),
                        cb.extract(LocalTimeField.SECOND, root.get("timeVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("timeParam", 12);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(4L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractYearFromDate() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "dateParam"),
                        cb.extract(LocalDateField.YEAR, root.get("dateVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("dateParam", 1918);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(5L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractMonthFromDate() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "dateParam"),
                        cb.extract(LocalDateField.MONTH, root.get("dateVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("dateParam", 9);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(5L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractDayFromDate() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "dateParam"),
                        cb.extract(LocalDateField.DAY, root.get("dateVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("dateParam", 28);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(5L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractQuarterFromDate() {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "dateParam"),
                        cb.extract(LocalDateField.QUARTER, root.get("dateVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                query.setParameter("dateParam", 3);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(5L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    public void testExtractWeekFromDate() {
        // Run only for Derby where we know it works fine
        if (!emf.getServerSession().getPlatform().isDerby()) {
            return;
        }
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<SyntaxEntity> cQuery = cb.createQuery(SyntaxEntity.class);
                Root<SyntaxEntity> root = cQuery.from(SyntaxEntity.class);
                cQuery.where(cb.equal(
                        cb.parameter(Integer.class, "dateParam"),
                        cb.extract(LocalDateField.WEEK, root.get("dateVal"))));
                TypedQuery<SyntaxEntity> query = em.createQuery(cQuery);
                // Number of the week for 28th September 1918 is 39
                query.setParameter("dateParam", 39);
                List<SyntaxEntity> result = query.getResultList();
                Assert.assertEquals(1, result.size());
                Assert.assertEquals(5L, result.get(0).getId());
                et.commit();
            } catch (Exception e) {
                et.rollback();
                throw e;
            }
        }
    }

    // Evaluate assertions on set of expected values
    private static <T> void assertFromSet(Set<T> expected, Collection<T> actual) {
        // Make sure to have mutable set
        Set<T> expectedInternal = new HashSet<>(expected);
        actual.forEach(
                value -> {
                    if (expectedInternal.contains(value)) {
                        expectedInternal.remove(value);
                    } else {
                        Assert.fail(String.format(
                                "Actual value %s is not from expected set of values %s",
                                value, setToString(expected)));
                    }
                }
        );
        // Make sure that all values from set were checked
        if (!expectedInternal.isEmpty()) {
            Assert.fail(String.format("Missing values %s from expected set %s",
                                      setToString(expectedInternal), setToString(expected)));
        }
    }

    private static <T> String setToString(Set<T> set) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean first = true;
        for (T item : set) {
            if (first) {
                first = false;
            } else {
                sb.append(',');
            }
            sb.append(item.toString());
        }
        sb.append(']');
        return sb.toString();
    }

}
