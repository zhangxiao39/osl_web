package com.osl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osl.mapper.GoodscategoryMapper;
import com.osl.mapper.entity.GoodsCategoryEntity;
import com.osl.model.GoodsCategoryModel;
import com.osl.service.GoodscategoryService;

@Service
public class GoodscategoryServiceImpl implements GoodscategoryService {

	@Autowired
	private GoodscategoryMapper goodscategoryMapper;

	@Override
	public List<GoodsCategoryModel> getCategoryAll() {
		// TODO Auto-generated method stub
		return goodscategoryMapper.getCategoryAll();
	}

	@Override
	public List<GoodsCategoryModel> getCategoryByC1() {
		// TODO Auto-generated method stub
		return goodscategoryMapper.getCategoryByC1();
	}

	@Override
	public List<GoodsCategoryModel> getCategoryByC2() {
		// TODO Auto-generated method stub
		return goodscategoryMapper.getCategoryByC2();
	}

	@Override
	public String setCategoryHtml() {
		StringBuilder _html = new StringBuilder();
		List<GoodsCategoryModel> c1 = this.getCategoryByC1();
		List<GoodsCategoryModel> c2 = this.getCategoryByC2();
		for (int i = 0; i < c1.size(); i++) {
			GoodsCategoryModel gc1 = c1.get(i);
			_html.append("<li class=\"jstree-open\" id=" + gc1.getId() + ">" + gc1.getName());
			_html.append("<ul>");
			for (int j = 0; j < c2.size(); j++) {
				GoodsCategoryModel gc2 = c2.get(j);

				if (gc2.getParentId() == gc1.getId()) {

					_html.append("<li id=" + gc2.getId() + ">" + gc2.getName());
					_html.append("</li>");
				}

			}
			_html.append("</ul>");
			_html.append("</li>");
		}
		return _html.toString();
	}

	@Override
	public int insertGoodsCategory(GoodsCategoryEntity _goodscategory) {
		// TODO Auto-generated method stub
		int _level = 1;
		String _position = "|0|";
		if (_goodscategory.getParentId() > 0) {
			GoodsCategoryEntity _parent = goodscategoryMapper.findById(_goodscategory.getParentId());
			_level = _parent.getLevel() + 1;
			_position = _parent.getPosition() + "|" + _parent.getId() + "|";
		}
		_goodscategory.setLevel(_level);
		_goodscategory.setPosition(_position);
		return goodscategoryMapper.insertGoodsCategory(_goodscategory);
	}

	@Override
	public int updateGoodscategoryName(GoodsCategoryEntity _goodscategory) {
		// TODO Auto-generated method stub
		long id = _goodscategory.getId();
		String name = _goodscategory.getName();
		return goodscategoryMapper.updateGoodscategoryName(id, name);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return goodscategoryMapper.deleteById(id);
	}

	@Override
	public String getGoodsCategorySelect() {
		// TODO Auto-generated method stub
		StringBuilder _html = new StringBuilder();
		List<GoodsCategoryModel> c1 = this.getCategoryByC1();
		List<GoodsCategoryModel> c2 = this.getCategoryByC2();
		_html.append("<option value=\"0\"  data-select2-id=\"0\">全部商品分类</option>");
		for (int i = 0; i < c1.size(); i++) {
			GoodsCategoryModel gc1 = c1.get(i);
			_html.append("<option value=\"" + gc1.getId() + "\">" + gc1.getName() + "</option>");
			for (int j = 0; j < c2.size(); j++) {
				GoodsCategoryModel gc2 = c2.get(j);
				if (gc2.getParentId() == gc1.getId()) {
					_html.append("<option value=\"" + gc2.getId() + "\">&#8195;&#8195;" + gc2.getName() + "</option>");
				}
			}
		}
		return _html.toString();
	}

	@Override
	public String getGoodsCategorySelect2() {
		// TODO Auto-generated method stub
		StringBuilder _html = new StringBuilder();
		List<GoodsCategoryModel> c1 = this.getCategoryByC1();
		List<GoodsCategoryModel> c2 = this.getCategoryByC2();
		_html.append("<option value=\"0\"  data-select2-id=\"0\">选择商品分类</option>");
		for (int i = 0; i < c1.size(); i++) {
			GoodsCategoryModel gc1 = c1.get(i);
			_html.append("<optgroup label=\"" + gc1.getName() + "\" data-select2-id=\"" + gc1.getId() + "\">");
			for (int j = 0; j < c2.size(); j++) {
				GoodsCategoryModel gc2 = c2.get(j);
				if (gc2.getParentId() == gc1.getId()) {
					_html.append("<option value=\"" + gc2.getId() + "\"  data-select2-id=\"" + gc2.getId() + "\">"
							+ gc2.getName() + "</option>");
				}
			}
			_html.append("</optgroup>");
		}
		return _html.toString();
	}

}
