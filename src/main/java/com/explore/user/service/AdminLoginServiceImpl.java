package com.explore.user.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.config.ResponseFlags;
import com.explore.user.entity.UserEntity;
import com.explore.user.localservice.UserLocalService;
import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.ResponseFlag;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.SessionHandleTypeEnum;
import com.wolf.framework.service.parameter.InputConfig;
import com.wolf.framework.service.parameter.OutputConfig;
import com.wolf.framework.session.Session;
import com.wolf.framework.session.SessionImpl;
import com.wolf.framework.worker.context.MessageContext;

/**
 *
 * @author jianying9
 */
@ServiceConfig(
        actionName = ActionNames.ADMIN_LOGIN,
        importantParameter = {
    @InputConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码")
},
        returnParameter = {
    @OutputConfig(name = "userId", typeEnum = TypeEnum.CHAR_32, desc = "用户id"),
    @OutputConfig(name = "userEmail", typeEnum = TypeEnum.CHAR_32, desc = "邮箱"),
    @OutputConfig(name = "nickName", typeEnum = TypeEnum.CHAR_32, desc = "昵称")
},
        validateSession = false,
        sessionHandleTypeEnum = SessionHandleTypeEnum.SAVE,
        response = true,
        description = "管理员用户登录,使用默认帐号",
        group = ActionGroupNames.USER,
        responseFlags = {
    @ResponseFlag(flag = ResponseFlags.FAILURE_PASSWORD_ERROR,
            description = "密码错误")
})
public class AdminLoginServiceImpl implements Service {

    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        String password = messageContext.getParameter("password");
        UserEntity userEntity = this.userLocalService.inquireAdminUser();
        //邮箱或则存在
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
