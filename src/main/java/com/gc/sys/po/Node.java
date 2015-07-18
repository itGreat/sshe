package com.gc.sys.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.gc.core.po.ID;


/**
 * @author gongchang
 * 描述：菜单节点
 * 时间：2014年9月4日 下午2:51:38
 */
@javax.persistence.Entity
@Table(name="t_sys_node")
public class Node extends ID{

    /*
     * 节点名称
     */
    private String name;

    /*
     * 序号
     */
    private Integer seq;
    /*
     * 层次
     */
    private Integer layer;
    /*
     * 父节点
     */
    private Node parent;

    /**
     * 子节点
     */
    private Set<Node> children;

    /*
     * 功能入口（实体）
     */
    private Entity entity;

    @Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 @Column
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	 @Column
	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="parent")
	@OrderBy("seq")
	public Set<Node> getChildren() {
		return children;
	}

	public void setChildren(Set<Node> children) {
		this.children = children;
	}

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="entity_id")
	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
