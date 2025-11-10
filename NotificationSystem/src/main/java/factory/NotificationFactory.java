package factory;

import enums.NotificationType;
import strategy.EmailGateway;
import strategy.NotificationGateway;
import strategy.PushGateway;
import strategy.SmsGateway;

import java.util.HashMap;
import java.util.Map;

public class NotificationFactory {
    private static final Map<NotificationType, NotificationGateway> gatewayMap = new HashMap<>();

    public static NotificationGateway createGateway(NotificationType type) {
        if (gatewayMap.containsKey(type)) {
            return gatewayMap.get(type);
        }

        NotificationGateway gateway = null;

        switch (type) {
            case EMAIL:
                gateway = new EmailGateway();
                break;
            case SMS:
                gateway = new SmsGateway();
                break;
            case PUSH:
                gateway = new PushGateway();
                break;
        }

        gatewayMap.put(type, gateway);
        return gateway;
    }
}
