package org.woo.orm.mybatis.mapper;


import org.woo.orm.entity.Goods;

import java.util.List;

public interface GoodsMapper {

    List<Goods> selectAllGoods() throws Exception;

    List<Goods> selectRecommendGoods() throws Exception;

}
