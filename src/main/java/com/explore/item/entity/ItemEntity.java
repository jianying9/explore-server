package com.explore.item.entity;

import com.explore.config.TableNames;
import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import com.wolf.framework.data.BasicTypeEnum;
import com.wolf.framework.service.parameter.Parameter;
import com.wolf.framework.service.parameter.ParameterConfig;
import com.wolf.framework.service.parameter.ParametersConfig;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.ITEM)
@ParametersConfig()
public final class ItemEntity extends Entity implements Parameter {

    @ParameterConfig(basicTypeEnum = BasicTypeEnum.LONG, desc = "物品id")
    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "物品id")
    private String itemId;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.CHAR_32, desc = "名称")
    @RColumnConfig(desc = "名称")
    private String itemName;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.IMAGE, desc = "图片文件内容")
    @RColumnConfig(desc = "文件内容")
    private String dataUrl;
    //
    @RColumnConfig(desc = "描述")
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.CHAR_120, desc = "密码md5")
    private String desc;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.LONG, desc = "花费点数")
    @RColumnConfig(desc = "花费点数")
    private long point;

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public String getDesc() {
        return desc;
    }

    public long getPoint() {
        return point;
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
        map.put("dataUrl", this.dataUrl);
        map.put("desc", this.desc);
        map.put("point", Long.toString(this.point));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.itemId = entityMap.get("itemId");
        this.itemName = entityMap.get("itemName");
        this.dataUrl = entityMap.get("dataUrl");
        this.desc = entityMap.get("desc");
        this.point = Long.parseLong(entityMap.get("point"));
    }
}
