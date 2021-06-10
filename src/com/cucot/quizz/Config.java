package com.cucot.quizz;

import java.util.Optional;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * https://blogs.oracle.com/javamagazine/java-quiz-optional-null-exception
 * This class demonstrates how Optional should be used and introduce a concept call thunk
 * In short words, this is about lazy evaluation of expression. Passing value expression instead of value.
 * The call to evaluate the value will be made only when necessary
 */
public class Config extends Properties {

    BiFunction<Boolean, String, Optional<String>> compose = (keyPresent, value) ->
            keyPresent ? Optional.of(value) : Optional.empty();

    public String getProperty(String key) {
        String value = super.getProperty(key);
        if (value == null) {
            throw new ConfigException(String.format("Property missing for key '%s'", key));
        }
        return value;
    }

    public Optional<String> getStringProperty(String key) {
        return compose.apply(this.containsKey(key), this.getProperty(key));
    }

    /**
     * enhanced version
     */

    BiFunction<Boolean, Supplier<String>, Optional<String>> enhancedCompose = (keyPresent, valueSupplier) ->
            keyPresent ? Optional.of(valueSupplier.get()) : Optional.empty();

    public Optional<String> getStringPropertyEnhanced(String key) {
        return enhancedCompose.apply(this.contains(key), () -> this.getProperty(key));
    }

    static class ConfigException extends RuntimeException {

        public ConfigException(String message) {
            super(message);
        }

        public ConfigException(String message, Throwable cause) {
            super(message, cause);
        }

        public ConfigException() {

        }
    }
}
