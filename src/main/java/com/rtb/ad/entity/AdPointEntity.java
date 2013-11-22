package com.rtb.ad.entity;

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
        tableName = "RtbAdPoint")
@ParametersConfig()
public class AdPointEntity extends Entity implements Parameter {

    @ParameterConfig(basicTypeEnum = BasicTypeEnum.UUID, desc = "广告id")
    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "广告id")
    private String adId;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.INT, desc = "广告剩余点数")
    @RColumnConfig(desc = "广告剩余点数", defaultValue = "0")
    private int adPoint;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.DATE_TIME, desc = "创建时间")
    @RColumnConfig(desc = "创建时间", defaultValue = "1384957981862")
    private long createTime;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.DATE_TIME, desc = "最后修改时间")
    @RColumnConfig(desc = "最后修改时间", defaultValue = "1384957981862")
    private long lastUpdateTime;

    public String getAdId() {
        return adId;
    }

    public int getAdPoint() {
        return this.adPoint;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public String getKeyValue() {
        return this.adId;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(4, 1);
        map.put("adId", this.adId);
        map.put("adPoint", Integer.toString(this.adPoint));
        map.put("createTime", Long.toString(this.createTime));
        map.put("lastUpdateTime", Long.toString(this.lastUpdateTime));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.adId = entityMap.get("adId");
        this.adPoint = Integer.parseInt(entityMap.get("adPoint"));
        this.createTime = Long.parseLong(entityMap.get("createTime"));
        this.lastUpdateTime = Long.parseLong(entityMap.get("lastUpdateTime"));
    }
}
