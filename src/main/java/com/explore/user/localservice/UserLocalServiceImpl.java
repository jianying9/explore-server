package com.explore.user.localservice;

import com.explore.user.entity.UserEmailEntity;
import com.explore.user.entity.UserEntity;
import com.explore.user.entity.UserNickNameEntity;
import com.wolf.framework.dao.REntityDao;
import com.wolf.framework.dao.annotation.InjectRDao;
import com.wolf.framework.dao.condition.InquireRedisIndexContext;
import com.wolf.framework.local.LocalServiceConfig;
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

    @Override
    public void init() {
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
        UserEntity userEntity = this.userEntityDao.insertAndInquire(parameterMap);
        //保存userEmail和userId映射
        Map<String, String> insertMap = userEntity.toMap();
        this.userEmailEntityDao.insert(insertMap);
        //保存nickName和userId映射
        this.userNickNameEntityDao.insert(insertMap);
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
}
