package strategy.formatter;

import entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}