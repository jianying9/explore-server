package com.explore.user.service;

import com.explore.config.ActionGroupNames;
import com.explore.config.ActionNames;
import com.explore.config.ResponseFlags;
import com.explore.user.entity.UserEntity;
import com.explore.user.entity.UserPointEntity;
import com.explore.user.localservice.UserLocalService;
import com.wolf.framework.local.InjectLocalService;
import com.wolf.framework.service.Service;
import com.wolf.framework.service.ServiceConfig;
import com.wolf.framework.session.Session;
import com.wolf.framework.worker.context.MessageContext;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aladdin
 */
@ServiceConfig(
        actionName = ActionNames.CONSUME_POINT,
        importantParameter = {"consumePoint"},
        returnParameter = {"consumePoint"},
        parametersConfigs = {UserPointEntity.class},
        response = true,
        group = ActionGroupNames.USER,
        description = "用户消费积分")
public class ConsumePointServiceImpl implements Service {

    @InjectLocalService()
    private UserLocalService userLocalService;

    @Override
    public void execute(MessageContext messageContext) {
        Session session = messageContext.getSession();
        String userId = session.getUserId();
        UserEntity userEntity = this.userLocalService.inquireUserByUserId(userId);
        if (userEntity == null) {
            messageContext.setFlag(ResponseFlags.FAILURE_USER_ID_NOT_EXIST);
        } else {
            long point = Long.parseLong(messageContext.getParameter("consumePoint"));
            //查询用户积分
            UserPointEntity userPointEntity = this.userLocalService.inquireUserPointByUserId(userId);
            if (userPointEntity == null) {
                messageContext.setFlag(ResponseFlags.FAILURE_USER_POINT_LESS);
            } else {
                long myPoint = userPointEntity.getMyPoint();
                long promoterPoint = userPointEntity.getPromoterPoint();
                long consumePoint = userPointEntity.getConsumePoint();
                long currentPoint = myPoint + promoterPoint - consumePoint;
                if (currentPoint >= point) {
                    long newPoint = this.userLocalService.increaseConsumePoint(userId, point);
                    Map<String, String> resultMap = new HashMap<String, String>(2, 1);
                    resultMap.put("consumePoint", Long.toString(newPoint));
                    messageContext.setMapData(resultMap);
                    messageContext.success();
                } else {
                    messageContext.setFlag(ResponseFlags.FAILURE_USER_POINT_LESS);
                }
            }
        }
    }
}
