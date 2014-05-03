package com.explore.item.entity;

import com.explore.config.TableNames;
import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.ITEM)
public final class ItemEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "物品id")
    private String itemId;
    //
    @RColumnConfig(desc = "名称")
    private String itemName;
    //
    @RColumnConfig(desc = "文件路径")
    private String fileUrl;
    //
    @RColumnConfig(desc = "描述")
    private String desc;
    //
    @RColumnConfig(desc = "出售价格(分)")
    private long price;
    //
    @RColumnConfig(desc = "成本(分)")
    private long cost;
    //
    @RColumnConfig(desc = "折扣百分比")
    private long discount;

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDataUrl() {
        return fileUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public long getPrice() {
        return price;
    }

    public long getCost() {
        return cost;
    }

    public long getDiscount() {
        return discount;
    }


    @Override
    public String getKeyValue() {
        return this.itemId;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(8, 1);
        map.put("itemId", this.itemId);
        map.put("itemName", this.itemName);
        map.put("fileUrl", this.fileUrl);
        map.put("desc", this.desc);
        map.put("price", Long.toString(this.price));
        map.put("cost", Long.toString(this.cost));
        map.put("discount", Long.toString(this.discount));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.itemId = entityMap.get("itemId");
        this.itemName = entityMap.get("itemName");
        this.fileUrl = entityMap.get("fileUrl");
        this.desc = entityMap.get("desc");
        this.price = Long.parseLong(entityMap.get("price"));
        this.cost = Long.parseLong(entityMap.get("cost"));
        this.discount = Long.parseLong(entityMap.get("discount"));
    }
}
