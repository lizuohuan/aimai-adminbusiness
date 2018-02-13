package com.magic.aimai.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单类.
 * @author hep
 *
 */
public class Menu  implements Serializable {

	private Integer id;

	private String title;

	private String href;

	private List<Menu> child = new ArrayList<Menu>();

	/** 是否选中 */
	private boolean spread;

	/** 菜单图标样式 */
	private String icon;

	/** 父级菜单id */
	private Integer parentId;

	/** 菜单级别 */
	private Integer level;

	/**
	 * 无参构造函数
	 */
	public Menu(){}

	/**
	 * 创建没有链接的父菜单.
	 * @param title
	 */
	public Menu(String title, String icon) {
		this.title = title;
		this.icon = icon;
		child = new ArrayList<Menu>();
	}

	/**
	 * 创建指向页面的菜单.
	 * @param title
	 * @param href
	 */
	public Menu(String title, String href, String icon) {
		this.title = title;
		this.href = href;
		this.icon = icon;
		child = new ArrayList<Menu>();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}

