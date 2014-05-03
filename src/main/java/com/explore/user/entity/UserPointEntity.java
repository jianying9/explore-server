package com.explore.user.entity;

import com.explore.config.TableNames;
import com.wolf.framework.dao.Entity;
import com.wolf.framework.dao.annotation.ColumnTypeEnum;
import com.wolf.framework.dao.annotation.RColumnConfig;
import com.wolf.framework.dao.annotation.RDaoConfig;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.USER_POINT)
public final class UserPointEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "用户ID")
    private String userId;
    //
    @RColumnConfig(desc = "用户赚取的点数", defaultValue = "0")
    private long myPoint;
    //
    @RColumnConfig(desc = "历史消费的点数", defaultValue = "0")
    private long consumePoint;

    public String getUserId() {
        return userId;
    }

    public long getMyPoint() {
        return myPoint;
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
        map.put("consumePoint", Long.toString(consumePoint));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.userId = entityMap.get("userId");
        this.myPoint = Long.parseLong(entityMap.get("myPoint"));
        this.consumePoint = Long.parseLong(entityMap.get("consumePoint"));
    }
}
