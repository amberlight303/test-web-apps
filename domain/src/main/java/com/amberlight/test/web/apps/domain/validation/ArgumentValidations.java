package com.amberlight.test.web.apps.domain.validation;


import com.amberlight.test.web.apps.domain.monetary.MonetaryAmount;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Utility class that performs common argument validations. To enable more
 * complex fluent validations for a single argument a companion
 * {@link ArgumentValidation} class with matching API is provided.
 * <p>
 * Sample usage:
 *
 * <pre>
 * String argument = &quot;123&quot;;
 * ArgumentValidations.notNull(&quot;test&quot;, argument);
 * ArgumentValidations.positiveAmount(&quot;test&quot;, argument);
 * </pre>
 *
 * which is equivalent to:
 *
 * <pre>
 * ArgumentValidations.validatorFor(&quot;test&quot;, argument).notNull().positiveAmount()
 * 		.validate();
 * </pre>
 */
public class ArgumentValidations {

    /**
     * Obtains a new argument validator for the provided argument.
     *
     * @param <T>
     *            argument type
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument to validate.
     *
     * @return a new argument validator for the provided argument.
     */
    public static <T> ArgumentValidation<T> validatorFor(String argumentName,
                                                         T argument) {
        return new ArgumentValidation<>(argumentName, argument);
    }

    /**
     * Ensures that the provided string argument has a minimum length of
     * {@code minLength}.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @param minLength
     *            minimum allowed length
     *
     * @throws IllegalStateException
     *             if the argument is null, empty or has an invalid length
     */
    public static void minLength(String argumentName, String argument,
                                 int minLength) {

        if (ArgumentPredicates.not(ArgumentPredicates.minLength(minLength),
                argument)) {

            throw new IllegalStateException(
                    String.format(
                            "The argument %s must have a length of at least %s, was %s",
                            argumentName, minLength, argument));
        }
    }

    /**
     * Ensures that the provided argument is not null and thrown an exception if
     * it is.
     *
     * @param argumentName
     *            name of the argument
     * @param argument
     *            argument to validate.
     *
     *
     * @throws IllegalStateException
     *             if the argument is null.
     */
    public static void notNull(String argumentName, Object argument) {

        if (ArgumentPredicates.is(ArgumentPredicates.NULL, argument)) {

            throw new IllegalStateException(String.format(
                    "The argument %s must not be null", argumentName));
        }
    }

    // todo fix typos and style in metadata everywhere

    /**
     * Ensures that the provided argument is null, and throws an exception if
     * it is not.
     *
     * @param argumentName
     *            name of the argument
     * @param argument
     *            argument to validate.
     *
     *
     * @throws IllegalStateException
     *             if the argument is not null.
     */
    public static void isNull(String argumentName, Object argument) {
        if (ArgumentPredicates.not(ArgumentPredicates.NULL, argument)) {
            throw new IllegalStateException(String.format(
                    "The argument %s must be null", argumentName));
        }
    }

    /**
     * Ensures that the provided argument does not have an empty string
     * representation throws an exception if it does.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null or else has an empty string
     *             representation.
     */
    public static void notEmpty(String argumentName, Object argument) {

        notNull(argumentName, argument);

        if (ArgumentPredicates.is(ArgumentPredicates.EMPTY, argument)) {
            throw new IllegalStateException(String.format(
                    "The argument %s must not be empty", argumentName));
        }
    }

    /**
     * Ensures that the provided argument is a valid number.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument to validate
     *
     * @throws IllegalStateException
     *             if the argument is not a valid number.
     */
    public static void number(String argumentName, Object argument) {

        notEmpty(argumentName, argument);

        if (ArgumentPredicates.not(ArgumentPredicates.NUMBER, argument)) {

            throw new IllegalStateException(String.format(
                    "The argument %s is not a valid number, was: %s",
                    argumentName, argument));
        }
    }

    /**
     * Ensures that the provided number argument is a valid, positive, number
     * and throws an exception if it is not.
     *
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null or is not positive.
     */
    public static void positiveNumber(String argumentName, Object argument) {

        number(argumentName, argument);

        if (ArgumentPredicates.not(ArgumentPredicates.POSITIVE, new BigDecimal(
                argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s must be positive, was: %s", argumentName,
                    argument));
        }
    }

    /**
     * Ensures that the provided argument (collection) is not null and not empty,
     * and throws an exception if it is
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument (collection) to validate.
     *
     * @throws IllegalStateException
     *             if the argument (collection) is null or empty
     */
    public static void notEmptyCollection(String argumentName, Collection<?> argument) {
        notNull(argumentName, argument);
        if (argument.isEmpty()) {
            throw new IllegalStateException(String.format(
                    "The argument (collection) %s must be not empty, size was: 0", argumentName));
        }
    }

    /**
     * Ensures that the provided argument (map) is not null and not empty,
     * and throws an exception if it is
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument (map) to validate.
     *
     * @throws IllegalStateException
     *             if the argument (map) is null or empty
     */
    public static void notEmptyMap(String argumentName, Map<?, ?> argument) {
        notNull(argumentName, argument);
        if (argument.isEmpty()) {
            throw new IllegalStateException(String.format(
                    "The argument (map) %s must be not empty, size was: 0", argumentName));
        }
    }

    /**
     * Ensures that the provided number argument is a valid, non-negative,
     * number and throws an exception if it is not.
     *
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null or is negative.
     */
    public static void notNegativeNumber(String argumentName, Object argument) {

        number(argumentName, argument);

        if (ArgumentPredicates.is(ArgumentPredicates.NEGATIVE, new BigDecimal(
                argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s must not be negative, was: %s",
                    argumentName, argument));
        }
    }

    /**
     * Ensures that the provided argument is a valid monetary amount according
     * to the specification in MonetaryAmount and throws an exception if
     * it is not.
     *
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null, empty or else has an invalid amount
     *             format.
     */
    public static void amount(String argumentName, Object argument) {

        number(argumentName, argument);

        if (ArgumentPredicates.not(ArgumentPredicates.MONETARY_MAX_AMOUNT,
                new BigDecimal(argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s must not exceed %s, was: %s",
                    argumentName, MonetaryAmount.MAX_MONETARY_AMOUNT, argument));
        }

        if (ArgumentPredicates.not(ArgumentPredicates.MONETARY_MAX_SCALE,
                new BigDecimal(argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s scale must not exceed %s, was: %s",
                    argumentName, MonetaryAmount.SCALE, argument));
        }
    }

    /**
     * Ensures that the provided string argument is a valid, positive, amount
     * and throws an exception if it is not.
     *
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null, empty or has an invalid number
     *             format or is not positive.
     */
    public static void positiveAmount(String argumentName, Object argument) {

        amount(argumentName, argument);

        if (ArgumentPredicates.not(ArgumentPredicates.POSITIVE, new BigDecimal(
                argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s must be positive, was: %s", argumentName,
                    argument));
        }
    }

    /**
     * Ensures that the provided argument is a valid, non-negative, amount and
     * throws an exception if it is not.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null, empty or has an invalid number
     *             format or is negative
     */
    public static void notNegativeAmount(String argumentName, Object argument) {

        amount(argumentName, argument);

        if (ArgumentPredicates.is(ArgumentPredicates.NEGATIVE, new BigDecimal(
                argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s must not be negative, was %s",
                    argumentName, argument));
        }
    }

    /**
     * Ensures that the provided string argument has a maximum length of
     * {@code maxLength}.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @param maxLength
     *            maximum allowed length
     *
     * @throws IllegalStateException
     *             if the argument is null, empty or has an invalid length
     */
    public static void maxLength(String argumentName, String argument,
                                 int maxLength) {

        if (ArgumentPredicates.not(ArgumentPredicates.maxLength(maxLength),
                argument)) {

            throw new IllegalStateException(String.format(
                    "The argument %s may have a length of at most %s, was: %s",
                    argumentName, maxLength, argument));
        }
    }

    /**
     * Argument validation for a specific set of arguments. Enabling more fluent
     * validations on a parameter. For example:
     *
     * <pre>
     * <code>
     * String value = "0.1";
     *
     * ArgumentValidator<String> p = ArgumentValidations.validatorFor("value", value);
     * p.notNull().positiveAmount().validate();
     * </code>
     * </pre>
     *
     */
    @Value
    public static class ArgumentValidation<T> {

        private final String argumentName;
        private final T argument;

        private final List<Validation> validations = Lists.newLinkedList();

        /**
         * Performs validation.
         */
        public void validate() {
            for (Validation validation : this.validations) {
                validation.validate();
            }
        }

        /**
         * Performs validation if the wrapped argument is not null.
         */
        public void validateIfArgumentIsNotNull() {
            if (this.argument != null) {
                validate();
            }
        }

        /**
         * Performs validation if the wrapped argument matches the provided
         * predicates.
         *
         * @param predicates
         *            list of predicates to check
         */
        @SafeVarargs
        public final void validateIfArgumentIs(Predicate<T>... predicates) {
            for (Predicate<T> predicate : predicates) {
                if (ArgumentPredicates.not(predicate, getArgument())) {
                    return;
                }
            }
            validate();
        }

        /**
         * Interface for delegates that executes an actual validation.
         */
        private interface Validation {

            /**
             * Performs a validation.
             */
            void validate();
        }

        /**
         * Ensures that the argument is not null and thrown an exception if it
         * is.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null.
         */
        public ArgumentValidation<T> notNull() {

            this.validations.add(() -> ArgumentValidations.notNull(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the argument does not have an empty string
         * representation and thrown an exception if it is.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null or else has an empty string
         *             representation.
         */
        public ArgumentValidation<T> notEmpty() {

            this.validations.add(() -> ArgumentValidations.notEmpty(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the provided argument is a valid number and throws an
         * exception if it is not.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or else not a valid number
         */
        public ArgumentValidation<T> number() {

            this.validations.add(() -> ArgumentValidations.number(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the provided argument is a valid, positive, number and
         * throws an exception if it is not.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null or not positive
         */
        public ArgumentValidation<T> positiveNumber() {

            this.validations.add(() -> ArgumentValidations.positiveNumber(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the provided argument is a valid, non-negative, number
         * and throws an exception if it is not.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null or negative
         */
        public ArgumentValidation<T> notNegativeNumber() {

            this.validations.add(() -> ArgumentValidations.notNegativeNumber(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the provided argument is a valid amount and throws an
         * exception if it is not.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or else has an invalid
         *             number format.
         */
        public ArgumentValidation<T> amount() {

            this.validations.add(() -> ArgumentValidations.amount(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the provided string argument is a valid, positive,
         * amount and throws an exception if it is not.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or has an invalid number
         *             format or is not positive.
         */
        public ArgumentValidation<T> positiveAmount() {

            this.validations.add(() -> ArgumentValidations.positiveAmount(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the provided string argument is a valid, non-negative,
         * amount and throws an exception if it is not.
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or has an invalid number
         *             format or is negative
         */
        public ArgumentValidation<T> notNegativeAmount() {

            this.validations.add(() -> ArgumentValidations.notNegativeAmount(
                    getArgumentName(), getArgument()));

            return this;
        }

        /**
         * Ensures that the argument has a minimum length of {@code minLength}.
         *
         * @param minLength
         *            minimum allowed length
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or has an invalid length.
         */
        public ArgumentValidation<T> minLength(final int minLength) {

            this.validations.add(() -> ArgumentValidations.minLength(
                    getArgumentName(), getArgument().toString(), minLength));

            return this;
        }

        /**
         * Ensures that the argument has a maximum length of {@code maxLength}.
         *
         * @param maxLength
         *            maximum allowed length
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or has an invalid length
         */
        public ArgumentValidation<T> maxLength(final int maxLength) {

            this.validations.add(() -> ArgumentValidations.maxLength(
                    getArgumentName(), getArgument().toString(), maxLength));

            return this;
        }

        /**
         * Ensures that argument has a minimum length of {@code minLength} and a
         * maximum length of {@code maxLength}.
         *
         *
         * @param minLength
         *            minimum allowed length
         *
         * @param maxLength
         *            maximum allowed length
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or has an invalid length
         */
        public ArgumentValidation<T> length(final int minLength,
                                            final int maxLength) {

            this.validations.add(() -> ArgumentValidations.length(
                    getArgumentName(), getArgument().toString(), minLength,
                    maxLength));

            return this;
        }

        /**
         * Ensures that the argument has a minimum value of {@code minValue}.
         *
         * @param minValue
         *            minimum allowed value
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or below the specified min
         *             value.
         */
        public ArgumentValidation<T> minValue(final int minValue) {

            this.validations.add(() -> ArgumentValidations.minValue(
                    getArgumentName(), getArgument(), minValue));

            return this;
        }

        /**
         * Ensures that the argument has a maximum length of {@code maxValue}.
         *
         * @param maxValue
         *            maximum allowed value
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null, empty or above the specified max
         *             value.
         */
        public ArgumentValidation<T> maxValue(final int maxValue) {

            this.validations.add(() -> ArgumentValidations.maxValue(
                    getArgumentName(), getArgument(), maxValue));

            return this;
        }

        /**
         * Ensures that argument has a minimum value of {@code minValue} and a
         * maximum length of {@code maxValue}.
         *
         *
         * @param minValue
         *            minimum allowed value
         *
         * @param maxValue
         *            maximum allowed value
         *
         * @return this instance
         *
         * @throws IllegalStateException
         *             if the argument is null or not in the specified range
         */
        public ArgumentValidation<T> valueBetween(final int minValue,
                                                  final int maxValue) {

            this.validations.add(() -> ArgumentValidations.valueBetween(
                    getArgumentName(), getArgument(), minValue, maxValue));

            return this;
        }

        /**
         * Ensures that the provided string argument represents a valid date
         * string.
         *
         * @throws IllegalStateException
         *             if the argument is null or not a valid date string.
         *
         * @return this instance
         */
        public ArgumentValidation<T> date() {

            this.validations.add(() -> ArgumentValidations.date(
                    getArgumentName(), getArgument().toString()));

            return this;
        }

        /**
         * Short-cut to {@link ArgumentValidations#validatorFor(String, Object)}
         * to enable more fluent parameter validations.
         *
         * @param <E>
         *            argument type
         *
         * @param argumentName
         *            name of the argument
         *
         * @param argument
         *            argument to validate.
         *
         * @return a new argument validator for the provided argument.
         */
        public <E> ArgumentValidation<E> validatorFor(String argumentName,
                                                      E argument) {
            return new ArgumentValidation<>(argumentName, argument);
        }
    }

    /**
     * Ensures that the provided string argument has a minimum length of
     * {@code minLength} and a maximum length of {@code maxLength}.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @param minLength
     *            minimum allowed length
     *
     * @param maxLength
     *            maximum allowed length
     *
     * @throws IllegalStateException
     *             if the argument is null, empty or has an invalid length
     */
    public static void length(String argumentName, String argument,
                              int minLength, int maxLength) {

        if (ArgumentPredicates.not(
                ArgumentPredicates.length(minLength, maxLength), argument)) {

            throw new IllegalStateException(
                    String.format(
                            "The argument %s must be between %s and %s characters long, was: %s",
                            argumentName, minLength, maxLength, argument));
        }
    }

    /**
     * Ensures that the provided number argument has a minimum value of
     * {@code minValue}.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @param minValue
     *            minimum allowed value
     *
     * @throws IllegalStateException
     *             if the argument is null, or below the specified min value.
     */
    public static void minValue(String argumentName, Object argument,
                                int minValue) {

        if (ArgumentPredicates.not(ArgumentPredicates.minValue(minValue),
                new BigDecimal(argument.toString()))) {

            throw new IllegalStateException(
                    String.format(
                            "The argument %s must have a value of at least %s, was: %s",
                            argumentName, minValue, argument));
        }
    }

    /**
     * Ensures that the provided number argument has a maximum value of
     * {@code maxValue}.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @param maxValue
     *            maximum allowed value
     *
     * @throws IllegalStateException
     *             if the argument is null, or above the specified max value.
     */
    public static void maxValue(String argumentName, Object argument,
                                int maxValue) {

        if (ArgumentPredicates.not(ArgumentPredicates.maxValue(maxValue),
                new BigDecimal(argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s may have a value of at most %s, was: %s",
                    argumentName, maxValue, argument));
        }
    }

    /**
     * Ensures that the provided number argument has a minimum value of
     * {@code minValue} and a maximum value of {@code maxValue}.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            amount argument to validate.
     *
     * @param minValue
     *            minimum allowed value
     *
     * @param maxValue
     *            maximum allowed value
     *
     * @throws IllegalStateException
     *             if the argument is null or not in the specified range.
     */
    public static void valueBetween(String argumentName, Object argument,
                                    int minValue, int maxValue) {

        if (ArgumentPredicates.not(
                ArgumentPredicates.valueBetween(minValue, maxValue),
                new BigDecimal(argument.toString()))) {

            throw new IllegalStateException(String.format(
                    "The argument %s must be between %s and %s, was: %s",
                    argumentName, minValue, maxValue, argument));
        }
    }

    /**
     * Ensures that the provided string argument represents a valid date string.
     *
     * @param argumentName
     *            name of the argument
     *
     * @param argument
     *            argument to validate.
     *
     * @throws IllegalStateException
     *             if the argument is null or not a valid date string.
     */
    public static void date(String argumentName, Object argument) {

        if (ArgumentPredicates.not(ArgumentPredicates.DATE_STRING,
                argument.toString())) {
            throw new IllegalStateException(String.format(
                    "The argument %s must be a valid date, was: %s",
                    argumentName, argument));
        }
    }

    /**
     * Convenience method to validate an argument using a predicate and throw an
     * appropriate exception if the predicate evaluation fails.
     *
     * @param argument
     *            argument
     *
     * @param predicate
     *            validation predicate, should evaluate to {@code true} if the
     *            argument is valid.
     *
     * @param errorMessage
     *            error message to include if the argument is invalid
     *
     * @param <T>
     *            The type
     *
     * @throws IllegalStateException
     *             If the argument validation fails.
     */
    public static <T> void validateArgument(T argument, Predicate<T> predicate,
                                            String errorMessage) {

        if (!predicate.apply(argument)) {
            throw new IllegalStateException(errorMessage);
        }
    }
}

