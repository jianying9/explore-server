package com.explore.user.localservice;

import com.explore.user.entity.UserEntity;
import com.explore.user.entity.UserPointEntity;
import com.wolf.framework.local.Local;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aladdin
 */
public interface UserLocalService extends Local {

    public boolean isUserEmailExist(String userEmail);

    public boolean isNickNameExist(String nickName);

    public UserEntity inquireUserByUserEmail(String userEmail);

    public UserEntity inquireUserByNickName(String nickName);

    public UserEntity inquireUserByUserId(String userId);

    public List<UserEntity> inquireUserByUserIdList(List<String> userIdList);

    public UserEntity insertAndInquireUser(Map<String, String> parameterMap);

    public UserEntity updateUserAndInquire(Map<String, String> parameterMap);
    //
    public UserPointEntity inquireUserPointByUserId(String userId);
    
    public long increaseMyPoint(String userId, long point);
    
    public long increasePromoterPoint(String userId, long point);
    
    public long increaseConsumePoint(String userId, long point);
}
