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
 *
 * @author aladdin
 */
@RDaoConfig(
        tableName = TableNames.USER)
public final class UserEntity extends Entity {

    @RColumnConfig(columnTypeEnum = ColumnTypeEnum.KEY, desc = "用户ID")
    private String userId;
    //
    @RColumnConfig(desc = "昵称")
    private String nickName;
    //
    @RColumnConfig(desc = "密码md5")
    private String password;
    //
    @RColumnConfig(desc = "邮箱")
    private String userEmail;
    //
    @RColumnConfig(desc = "注册时间")
    private long createTime;

    public String getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public long getCreateTime() {
        return createTime;
    }

    @Override
    public String getKeyValue() {
        return this.userId;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(8, 1);
        map.put("userId", this.userId);
        map.put("nickName", this.nickName);
        map.put("password", this.password);
        map.put("userEmail", this.userEmail);
        map.put("createTime", Long.toString(this.createTime));
        return map;
    }

    @Override
    protected void parseMap(Map<String, String> entityMap) {
        this.userId = entityMap.get("userId");
        this.nickName = entityMap.get("nickName");
        this.password = entityMap.get("password");
        this.userEmail = entityMap.get("userEmail");
        this.createTime = Long.parseLong(entityMap.get("createTime"));
    }
}
