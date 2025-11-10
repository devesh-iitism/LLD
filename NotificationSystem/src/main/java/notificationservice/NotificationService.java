package notificationservice;

import decorator.RetryableGatewayDecorator;
import entities.Notification;
import factory.NotificationFactory;
import strategy.NotificationGateway;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private final ExecutorService executor;

    public NotificationService(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void sendNotification(Notification notification) {
        executor.submit(() -> {
            NotificationGateway gateway = new RetryableGatewayDecorator(
                    NotificationFactory.createGateway(notification.getType()),
                    3,
                    1000);
            try {
                gateway.send(notification);
            } catch (Exception e) {
                System.out.println("Exception while sending notification: " + e);
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
