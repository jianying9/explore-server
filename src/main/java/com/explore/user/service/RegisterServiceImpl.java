package com.explore.user.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.config.ResponseFlags;
import com.explore.user.entity.UserEntity;
import com.explore.user.localservice.UserLocalService;
import com.wolf.framework.data.TypeEnum;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.service.parameter.InputConfig;
import com.wolf.framework.service.parameter.OutputConfig;
import com.wolf.framework.worker.context.MessageContext;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.REGISTER,
        importantParameter = {
            @InputConfig(name = "password", typeEnum = TypeEnum.CHAR_32, desc = "密码"),
            @InputConfig(name = "userEmail", typeEnum = TypeEnum.CHAR_32, desc = "邮箱"),
            @InputConfig(name = "nickName", typeEnum = TypeEnum.CHAR_32, desc = "昵称")
        },
        minorParameter = {
            @InputConfig(name = "promoter", typeEnum = TypeEnum.CHAR_32, desc = "推广人id")
        },
        returnParameter = {
            @OutputConfig(name = "userId", typeEnum = TypeEnum.CHAR_32, desc = "用户id"),
            @OutputConfig(name = "userEmail", typeEnum = TypeEnum.CHAR_32, desc = "邮箱"),
            @OutputConfig(name = "nickName", typeEnum = TypeEnum.CHAR_32, desc = "昵称")
        },
        validateSession = false,
        response = true,
        requireTransaction = true,
        group = ActionGroupNames.USER,
        description = "用户注册:FAILURE_USER_EMAIL_USED-邮箱已经被使用")
public class RegisterServiceImpl implements Service {

    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Map<String, String> parameterMap = messageContext.getParameterMap();
        String userEmail = parameterMap.get("userEmail");
        synchronized (this) {
            //查询邮箱是否被使用
            boolean isExists = this.userLocalService.isUserEmailExist(userEmail);
            if (isExists) {
                //邮箱已经被使用
                messageContext.setFlag(ResponseFlags.FAILURE_USER_EMAIL_USED);
            } else {
                String nickName = parameterMap.get("nickName");
                isExists = this.userLocalService.isNickNameExist(nickName);
                if (isExists) {
                    //昵称已经被使用
                    messageContext.setFlag(ResponseFlags.FAILURE_USER_NICK_NAME_USED);
                } else {
                    //新增加用户
                    parameterMap.put("createTime", Long.toString(System.currentTimeMillis()));
                    if(parameterMap.containsKey("promoter") == false) {
                        parameterMap.put("promoter", "");
                    }
                    UserEntity userEntity = this.userLocalService.insertAndInquireUser(parameterMap);
                    messageContext.setEntityData(userEntity);
                    messageContext.success();
                }
            }
        }
    }
}
