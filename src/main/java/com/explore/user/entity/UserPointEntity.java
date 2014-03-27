package com.explore.user.entity;

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
        tableName = TableNames.USER_POINT)
@ParametersConfig()
public final class UserPointEntity extends Entity implements Parameter {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "用户ID")
    private String userId;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.INT, desc = "用户赚取的点数")
    @RColumnConfig(desc = "用户赚取的点数", defaultValue = "0")
    private long myPoint;
    //
    @RColumnConfig(desc = "推广赚取的点数", defaultValue = "0")
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.INT, desc = "推广赚取的点数")
    private long promoterPoint;
    //
    @ParameterConfig(basicTypeEnum = BasicTypeEnum.INT, desc = "历史消费的点数")
    @RColumnConfig(desc = "历史消费的点数", defaultValue = "0")
    private long consumePoint;

    public String getUserId() {
        return userId;
    }

    public long getMyPoint() {
        return myPoint;
    }

    public long getPromoterPoint() {
        return promoterPoint;
    }

    public long getConsumePoint() {
        return consumePoint;
    }

    @Override
    public String getKeyValue() {
        return this.userId;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(4, 1);
        map.put("userId", this.userId);
        map.put("myPoint", Long.toString(myPoint));
        map.put("promoterPoint", Long.toString(promoterPoint));
        map.put("consumePoint", Long.toString(consumePoint));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.userId = entityMap.get("userId");
        this.myPoint = Long.parseLong(entityMap.get("myPoint"));
        this.promoterPoint = Long.parseLong(entityMap.get("promoterPoint"));
        this.consumePoint = Long.parseLong(entityMap.get("consumePoint"));
    }
}
