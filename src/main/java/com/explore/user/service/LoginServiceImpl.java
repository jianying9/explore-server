package com.explore.user.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.config.ResponseFlags;
import com.explore.session.SessionImpl;
import com.explore.user.entity.UserEntity;
import com.explore.user.localservice.UserLocalService;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseFlag;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.SessionHandleTypeEnum;
import com.wolf.framework.session.Session;
import com.wolf.framework.worker.context.MessageContext;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.LOGIN,
        importantParameter = {"userEmail", "password"},
        returnParameter = {"userId", "userEmail", "nickName"},
        parametersConfigs = {UserEntity.class},
        validateSession = false,
        sessionHandleTypeEnum = SessionHandleTypeEnum.SAVE,
        response = true,
        description = "用户登录,可以使用邮箱或则昵称登录",
        group = ActionGroupNames.USER,
        responseFlags = {
    @ResponseFlag(flag = ResponseFlags.FAILURE_LOGIN_NOT_EXIST,
            description = "邮箱或昵称不存在")
})
public class LoginServiceImpl implements Service {

    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> parameterMap = messageContext.getParameterMap();
        String userEmail = parameterMap.get("userEmail");
        UserEntity userEntity = this.userLocalService.inquireUserByUserEmail(userEmail);
        if (userEntity == null) {
            //判断昵称是否存在
            userEntity = this.userLocalService.inquireUserByNickName(userEmail);
        }
        if (userEntity == null) {
            //登录帐号不存在
            messageContext.setFlag(ResponseFlags.FAILURE_LOGIN_NOT_EXIST);
        } else {
            //邮箱或则存在
            String password = parameterMap.get("password");
            if (userEntity.getPassword().equals(password)) {
                String userId = userEntity.getUserId();
                //密码正确
                Session session = new SessionImpl(userId);
                messageContext.setNewSession(session);
                messageContext.setEntityData(userEntity);
                messageContext.success();
            } else {
                //密码错误
                messageContext.setFlag(ResponseFlags.FAILURE_PASSWORD_ERROR);
            }
        }
    }
}
