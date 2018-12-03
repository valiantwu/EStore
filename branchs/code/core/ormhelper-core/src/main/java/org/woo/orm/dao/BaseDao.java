package org.woo.orm.dao;


import org.woo.dataentity.page.PageBean;
import org.woo.dataentity.page.PageParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK extends Serializable> {
    /**
     * 根据实体对象新增记录.
     *
     * @param entity .
     * @return id .
     */
    long insert(T entity);

    /**
     * 批量保存对象.
     *
     * @return id .
     */
    long insert(List<T> list);

    /**
     * 更新实体对应的记录.
     *
     * @param entity .
     * @return
     */
    int update(T entity);

    /**
     * 批量更新对象.
     *
     * @return int .
     */
    int update(List<T> list);

    /**
     * 根据ID查找记录.
     *
     * @param id .
     * @return entity .
     */
    T getById(PK id);

    /**
     * 根据ID删除记录.
     *
     * @param id .
     * @return
     */
    int deleteById(PK id);

    /**
     * 分页查询 .
     *
     * @param pageParam 分页参数.
     * @param paramMap  业务条件查询参数.
     * @return
     */
    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回集合
     */
    List<T> listBy(Map<String, Object> paramMap);

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param paramMap
     * @return 返回实体
     */
    T getBy(Map<String, Object> paramMap);

    /**
     * 根据ID获取实体对象.
     *
     * @param id 记录ID
     * @return 实体对象
     */

    T get(PK id);

    /**
     * 保存实体对象.
     *
     * @param entity 对象
     * @return ID
     */

    PK save(T entity);

    //分页查询
    List<T> queryForPage(final int offset, final int length, String hql, Map<String, Object> map);

    //总记录条数
    int getCount(String hql);

    boolean isExistByName(String hql, Map<String, Object> map);

    int deleteByPrimaryKey(String hql, Map<String, Object> map);

    int deleteByPrimaryKey(List<String> pkIds);

    int insert(String sql, Map<String, Object> map);

    int batchInsert(List<T> records);

    int insertSelective(T record);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(String sql, Map<String, Object> map);

    List<T> selectAll(String hql, Map<String, Object> map);

    List<T> selectByFiled(String hql, Map<String, Object> map);

    T selectByUniqueFiled(String hql, Map<String, Object> map);

    String getSeq(String sql);
}
