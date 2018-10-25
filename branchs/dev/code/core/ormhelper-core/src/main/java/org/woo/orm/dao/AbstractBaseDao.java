package org.woo.orm.dao;

import org.hibernate.SessionFactory;
import org.woo.dataentity.core.BaseEntity;
import org.woo.dataentity.page.PageBean;
import org.woo.dataentity.page.PageParam;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseDao<T extends BaseEntity, PK extends Serializable> implements BaseDao<T, PK> {
    private Class<T> entityClass;

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected SessionFactory sessionFactory;


    public AbstractBaseDao() {
        this.entityClass = null;
        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long insert(T entity) {
        return 0;
    }

    @Override
    public long insert(List<T> list) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public int update(List<T> list) {
        return 0;
    }

    @Override
    public T getById(PK id) {
        return null;
    }

    @Override
    public int deleteById(PK id) {
        return 0;
    }

    @Override
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public List<T> listBy(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public T getBy(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public T get(PK id) {
        return null;
    }

    @Override
    public PK save(T entity) {
        return null;
    }

    @Override
    public List<T> queryForPage(int offset, int length, String hql, Map<String, Object> map) {
        return null;
    }

    @Override
    public int getCount(String hql) {
        return 0;
    }

    @Override
    public boolean isExistByName(String hql, Map<String, Object> map) {
        return false;
    }

    @Override
    public int deleteByPrimaryKey(String hql, Map<String, Object> map) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(List<String> pkIds) {
        return 0;
    }

    @Override
    public int insert(String sql, Map<String, Object> map) {
        return 0;
    }

    @Override
    public int batchInsert(List<T> records) {
        return 0;
    }

    @Override
    public int insertSelective(T record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(String sql, Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<T> selectAll(String hql, Map<String, Object> map) {
        return null;
    }

    @Override
    public List<T> selectByFiled(String hql, Map<String, Object> map) {
        return null;
    }

    @Override
    public T selectByUniqueFiled(String hql, Map<String, Object> map) {
        return null;
    }

    @Override
    public String getSeq(String sql) {
        return null;
    }
}
