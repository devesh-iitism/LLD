package strategy.appender;

import strategy.formatter.LogFormatter;
import entities.LogMessage;
import strategy.formatter.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender {
    private LogFormatter formatter;

    public ConsoleAppender() {
        this.formatter = new SimpleTextFormatter();
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }

    @Override
    public void close() {}

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }
}