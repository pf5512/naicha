package com.naicha.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.naicha.app.service.PushService;
import com.naicha.app.service.UserService;
import com.naicha.app.utils.Codes;
import com.test.PushExample;

@Service
@Transactional
public class PushServiceImpl implements PushService {
	
	@Autowired
	private UserService userService;
	//log4j 日誌記錄
	protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);
	
	/**
	 * 推送申請消息
	 */
	@Override
	public Integer pushApplyMessage(int taskId, String tagUserIdStr) {
		String contentType =  Codes.MESSAGE_APPLY_TASK;
		String username = userService.findById(Integer.parseInt(tagUserIdStr)).getName();
		String content = "奶茶"+username+"报名你发布的任务了，点击查看。";
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        PushPayload payload = buildPushObject_android_and_ios(tagUserIdStr, content, taskId+"", contentType, username);
//        PushPayload payload = buildPushObject_android_tag_alertWithTitle();
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
			

		return null;
	}
	private static final String appKey ="5d0c695d6f9fdc6e69ae38a1";
	private static final String masterSecret = "938967141538356a8ab5243c";
	
	public static final String TITLE = "口语奶茶";
    public static final String ALERT = "严颜";
    public static final String MSG_CONTENT = "原来已经成功了，为什么上面不闪一下";
    public static final String CONTENT_TYPE = "1";
    
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";

	public static void main(String[] args) {
//        testSendPushWithCustomConfig();
//        testSendIosAlert();
	}
	
	
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
	
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag("yang"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }
    
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("time",new Date().toGMTString());
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("yang"))
                .setNotification(Notification.android(ALERT, TITLE, map))
                .setMessage(Message.content(MSG_CONTENT))
                .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios(String TagUserIdStr,
    		String content,String taskId,String contentType,String username) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(TagUserIdStr))//推送到指定目标用户
                .setNotification(Notification.newBuilder()
                		.setAlert(content)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.addExtra("taskId", taskId)
                				.addExtra("username", username)
                				.build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("taskId", taskId)
                				.addExtra("username", username)
                				.build())
                		.build())
                .setMessage(Message.newBuilder()
                		.setTitle("口语奶茶")
                		.setMsgContent(content)
                		.setContentType(contentType).build())
                .build();
    }
    
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
//                .setAudience(Audience.tag("yang"))
                .setAudience(Audience.alias("szu"))
                .setNotification(Notification.alert(ALERT))
//                .setNotification(Notification.newBuilder()
//                        .addPlatformNotification(IosNotification.newBuilder()
//                                .setAlert(ALERT)
//                                .setBadge(5)
//                                .setSound("happy")
//                                .addExtra("from", "JPush")
//                                .build())
//                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public static void testSendPushWithCustomConfig() {
        ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName("https://api.jpush.cn");
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3, null, config);
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert();

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    public static void testSendIosAlert() {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        IosAlert alert = IosAlert.newBuilder()
                .setTitleAndBody("test alert", "test ios alert json")
                .setActionLocKey("PLAY")
                .build();
        try {
            PushResult result = jpushClient.sendIosNotificationWithAlias(alert, new HashMap<String, String>(), "alias1");
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
}
