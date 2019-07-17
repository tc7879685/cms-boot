
package com.finance.model;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeModel implements Serializable {
	
	private static final long serialVersionUID = 4013193970046502756L;

	private String key;
	
	private String title;
	
	private List<TreeModel> children;

	private String parentId;

	private String value;

	public TreeModel(){

	}


	/*public TreeModel(MenuModel permission) {
		this.key = permission.getMenuCode();
		this.icon = permission.getIconSmall();
		this.parentId = permission.getParentCode();
		this.title = permission.getMenuName();
		this.slotTitle =  permission.getMenuName();
		this.value = permission.getMenuCode();
		this.isLeaf = permission.isLeaf();
		this.label = permission.getMenuName();
		if(!permission.isLeaf()) {
			this.children = new ArrayList<TreeModel>();
		}
	}
	 
	 public TreeModel(String key, String parentId, String slotTitle, Integer ruleFlag, boolean isLeaf) {
    	this.key = key;
    	this.parentId = parentId;
    	this.ruleFlag=ruleFlag;
    	this.slotTitle =  slotTitle;
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("title", "hasDatarule");
    	this.scopedSlots = map;
    	this.isLeaf = isLeaf;
    	this.value = key;
    	if(!isLeaf) {
    		this.children = new ArrayList<TreeModel>();
    	}
    }*/
	 

	
	




}

