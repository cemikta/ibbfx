/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.service;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.lagossoftware.ibbfx.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Abstract Service for all services.
 *
 * @param <T> base entity
 *
 * @author Cem Ikta
 */
public abstract class AbstractService<T extends BaseEntity> {

    @Inject
    private EntityManager entityManager;

    private final Class<T> entityClass;

    /**
     * Create default service
     *
     * @param entityClass entity class
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Gets injected entity manager.
     *
     * @return entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Gets entity class.
     *
     * @return entity class
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * Create entity.
     *
     * @param entity entity model.
     * @return created entity
     */
    @Transactional
    public T create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().refresh(entity);

        return entity;
    }

    /**
     * Update entity.
     *
     * @param entity entity model
     * @return updated entity
     */
    @Transactional
    public T update(T entity) {
        entity = getEntityManager().merge(entity);

        return entity;
    }

    /**
     * Create or update entity.
     *
     * @param entity entity model
     * @return created or updated entity
     */
    @Transactional
    public T createOrUpdate(T entity) {
        if (entity.getId() == null) {
            getEntityManager().persist(entity);

        } else {
            entity = getEntityManager().merge(entity);
        }

        return entity;
    }

    /**
     * Remove entity.
     *
     * @param entity entity model
     */
    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Find entity.
     *
     * @param id entity id
     * @return entity
     */
    @Transactional
    public T find(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Find one entity with named query and parameters.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @return entity with single result
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public T findOneWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
        Set<Entry<String, Object>> params = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        for (Entry<String, Object> entry : params) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
          return null;
        }
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param resultLimit limit for result list
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName, int resultLimit) {
        return getEntityManager().createNamedQuery(namedQueryName).
                setMaxResults(resultLimit).getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @return result list
     */
    @Transactional
    public List<T> getListWithNamedQuery(String namedQueryName,
            Map<String, Object> parameters) {

        return getListWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @param resultLimit limit for result list
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int resultLimit) {

        Set<Entry<String, Object>> params = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }

        for (Entry<String, Object> entry : params) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @param start start position
     * @param end end position
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int start, int end) {

        Set<Entry<String, Object>> params = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        for (Entry<String, Object> entry : params) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setMaxResults(end - start);
        query.setFirstResult(start);

        return query.getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param start start position
     * @param end end position
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName, int start, int end) {
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        query.setMaxResults(end - start);
        query.setFirstResult(start);

        return query.getResultList();
    }

    /**
     * Gets entity list with native sql.
     *
     * @param sql native sql
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListByNativeQuery(String sql) {
        return getEntityManager().createNativeQuery(sql, entityClass).getResultList();
    }

    /**
     * Gets entity list with native sql query.
     *
     * @param sql native sql query
     * @param resultLimit limit for result list
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListByNativeQuery(String sql, int resultLimit) {
        return getEntityManager().createNativeQuery(sql, entityClass)
                .setMaxResults(resultLimit).getResultList();
    }

    /**
     * Gets entity list with native sql query.
     *
     * @param sql native sql query
     * @param start start position
     * @param end end position
     * @return result list
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public List<T> getListByNativeQuery(String sql, int start, int end) {
        Query query = getEntityManager().createNativeQuery(sql, entityClass);
        query.setMaxResults(end - start);
        query.setFirstResult(start);

        return query.getResultList();
    }

}
