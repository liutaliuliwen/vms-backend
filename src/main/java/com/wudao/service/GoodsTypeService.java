package com.wudao.service;

import java.util.List;

import com.wudao.entity.GoodsType;

/**
 * 商品类别Service接口
 * @author wudao
 *
 */
public interface GoodsTypeService {

	/**
	 * 根据id查询商品类别实体
	 * @param id
	 * @return
	 */
	GoodsType findById(Integer id);
	
	/**
	 * 根据父节点查找商品类别
	 * @param parentId
	 * @return
	 */
	List<GoodsType> findByParentId(int parentId);
	
	/**
	 * 添加或者修改商品类别信息
	 * @param goodsType
	 */
	void save(GoodsType goodsType);
	
	/**
	 * 根据id删除商品类别信息
	 * @param id
	 */
	void delete(Integer id);


	/**
	 * 查找所有的商品类别信息
	 * @return
	 */
	List<GoodsType> findAllType();
}
