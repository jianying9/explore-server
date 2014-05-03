package com.explore.user.localservice;

import com.explore.config.TableNames;
import com.explore.key.localservice.KeyLocalService;
import com.explore.user.entity.UserEmailEntity;
import com.explore.user.entity.UserEntity;
import com.explore.user.entity.UserNickNameEntity;
import com.explore.user.entity.UserPointEntity;
import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.local.LocalServiceConfig;
import com.wolf.framework.utils.SecurityUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@LocalServiceConfig(
        interfaceInfo = UserLocalService.class,
        description = "用户操作内部接口")
public class UserLocalServiceImpl implements UserLocalService {

    @InjectRDao(clazz = UserEntity.class)
    private REntityDao<UserEntity> userEntityDao;
    //
    @InjectRDao(clazz = UserEmailEntity.class)
    private REntityDao<UserEmailEntity> userEmailEntityDao;
    //
    @InjectRDao(clazz = UserNickNameEntity.class)
    private REntityDao<UserNickNameEntity> userNickNameEntityDao;
    //
    @InjectRDao(clazz = UserPointEntity.class)
    private REntityDao<UserPointEntity> userPointEntityDao;
    //
    @InjectLocalService()
    private KeyLocalService keyLocalService;

    @Override
    public void init() {
        //初始化admin用户
        UserEntity adminEntity = this.userEntityDao.inquireByKey(ADMIN_ID);
        if (adminEntity == null) {
            final String userEmail = "ljy18659199765@gmail.com";
            final String nickName = "admin";
            final String password = SecurityUtils.encryptByMd5("zjp19881024");
            Map<String, String> adminMap = new HashMap<String, String>(4, 1);
            adminMap.put("userId", ADMIN_ID);
            adminMap.put("userEmail", userEmail);
            adminMap.put("nickName", nickName);
            adminMap.put("password", password);
            adminMap.put("createTime", Long.toString(System.currentTimeMillis()));
            this.userEntityDao.insert(adminMap);
        }
    }
    
    @Override
    public UserEntity inquireAdminUser() {
        return this.userEntityDao.inquireByKey(ADMIN_ID);
    }

    @Override
    public boolean isUserEmailExist(String userEmail) {
        boolean result = false;
        UserEmailEntity userEmailEntity = this.userEmailEntityDao.inquireByKey(userEmail);
        if (userEmailEntity != null) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isNickNameExist(String nickName) {
        boolean result = false;
        UserNickNameEntity userNickNameEntity = this.userNickNameEntityDao.inquireByKey(nickName);
        if (userNickNameEntity != null) {
            result = true;
        }
        return result;
    }

    @Override
    public UserEntity inquireUserByUserId(String userId) {
        return this.userEntityDao.inquireByKey(userId);
    }

    @Override
    public List<UserEntity> inquireUserByUserIdList(List<String> userIdList) {
        return this.userEntityDao.inquireByKeys(userIdList);
    }

    @Override
    public UserEntity insertAndInquireUser(Map<String, String> parameterMap) {
        long nextKeyValue = this.keyLocalService.nextKeyValue(TableNames.USER);
        parameterMap.put("userId", Long.toString(nextKeyValue));
        UserEntity userEntity = this.userEntityDao.insertAndInquire(parameterMap);
        //保存userEmail和userId映射
        Map<String, String> insertMap = userEntity.toMap();
        this.userEmailEntityDao.insert(insertMap);
        //保存nickName和userId映射
        this.userNickNameEntityDao.insert(insertMap);
        return userEntity;
    }

    @Override
    public UserEntity inquireUserByNickName(String nickName) {
        UserEntity userEntity;
        UserNickNameEntity userNickNameEntity = this.userNickNameEntityDao.inquireByKey(nickName);
        if (userNickNameEntity == null) {
            userEntity = null;
        } else {
            userEntity = this.userEntityDao.inquireByKey(userNickNameEntity.getUserId());
        }
        return userEntity;
    }

    @Override
    public UserEntity inquireUserByUserEmail(String userEmail) {
        UserEntity userEntity;
        UserEmailEntity userEmailEntity = this.userEmailEntityDao.inquireByKey(userEmail);
        if (userEmailEntity == null) {
            userEntity = null;
        } else {
            userEntity = this.userEntityDao.inquireByKey(userEmailEntity.getUserId());
        }
        return userEntity;
    }

    @Override
    public UserEntity updateUserAndInquire(Map<String, String> parameterMap) {
        return this.userEntityDao.updateAndInquire(parameterMap);
    }

    @Override
    public long increaseMyPoint(String userId, long point) {
        return this.userPointEntityDao.increase(userId, "myPoint", point);
    }

    @Override
    public long increaseConsumePoint(String userId, long point) {
        return this.userPointEntityDao.increase(userId, "consumePoint", point);
    }

    @Override
    public UserPointEntity inquireUserPointByUserId(String userId) {
        return this.userPointEntityDao.inquireByKey(userId);
    }
}
