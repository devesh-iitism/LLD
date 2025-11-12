package strategy.appender;

import entities.LogMessage;
import strategy.formatter.LogFormatter;

public interface LogAppender {
    void append(LogMessage logMessage);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter formatter);
}