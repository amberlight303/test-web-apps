package com.amberlight.test.web.apps.domain.validation;

import com.amberlight.test.web.apps.domain.monetary.BigDecimalUtil;
import com.amberlight.test.web.apps.domain.monetary.MonetaryAmount;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Utility class for more convenient use of predicates.
 */
public class ArgumentPredicates {

    public static final ArgumentPredicate<Object> NULL = new ArgumentPredicate<Object>() {

        @Override
        public boolean apply(Object input) {
            return Predicates.isNull().apply(input);
        }

        @Override
        public String getPredicateDescription() {
            return "null";
        }
    };
    public static final ArgumentPredicate<Object> EMPTY = new ArgumentPredicate<Object>() {

        @Override
        public boolean apply(Object input) {
            return input.toString().length() == 0;
        }

        @Override
        public String getPredicateDescription() {
            return "empty";
        }
    };

    public static final ArgumentPredicate<Object> NULL_OR_EMPTY = new ArgumentPredicate<Object>() {

        @Override
        public boolean apply(Object input) {
            return Predicates.or(Predicates.isNull(), EMPTY).apply(input);
        }

        @Override
        public String getPredicateDescription() {
            return "null or empty";
        }
    };

    public static final ArgumentPredicate<Object> NUMBER = new ArgumentPredicate<Object>() {

        @Override
        public boolean apply(Object input) {
            try {
                if (!(input instanceof Number)) {
                    new BigDecimal(input.toString());
                }
            } catch (NumberFormatException e) {
                return false;
            }

            return true;
        }

        @Override
        public String getPredicateDescription() {
            return "number";
        }
    };

    public static final ArgumentPredicate<BigDecimal> ZERO = new ArgumentPredicate<BigDecimal>() {

        @Override
        public boolean apply(BigDecimal input) {
            return BigDecimalUtil.isZero(input);
        }

        @Override
        public String getPredicateDescription() {
            return "must be positive";
        }
    };

    public static final ArgumentPredicate<BigDecimal> NEGATIVE = new ArgumentPredicate<BigDecimal>() {

        @Override
        public boolean apply(BigDecimal input) {
            return BigDecimalUtil.isNegative(input);
        }

        @Override
        public String getPredicateDescription() {
            return "must be positive";
        }
    };

    public static final ArgumentPredicate<BigDecimal> POSITIVE = new ArgumentPredicate<BigDecimal>() {

        @Override
        public boolean apply(BigDecimal input) {
            return BigDecimalUtil.isPositive(input);
        }

        @Override
        public String getPredicateDescription() {
            return "must be positive";
        }
    };

    public static final ArgumentPredicate<String> DATE_STRING = new ArgumentPredicate<String>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(String input) {
            try {
                LocalDate.parse(input);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getPredicateDescription() {
            return "must be a valid date";
        }
    };

    public static final ArgumentPredicate<BigDecimal> MONETARY_MAX_SCALE = new ArgumentPredicate<BigDecimal>() {

        @Override
        public boolean apply(BigDecimal input) {

            return input.scale() <= MonetaryAmount.SCALE;
        }

        @Override
        public String getPredicateDescription() {
            return "must have a scale below + " + MonetaryAmount.SCALE;
        }
    };

    public static final ArgumentPredicate<BigDecimal> MONETARY_MAX_AMOUNT = new ArgumentPredicate<BigDecimal>() {

        @Override
        public boolean apply(BigDecimal input) {

            return !BigDecimalUtil.isGreaterThan(input,
                    MonetaryAmount.MAX_MONETARY_AMOUNT);
        }

        @Override
        public String getPredicateDescription() {
            return "must be less than monetary max value "
                   + MonetaryAmount.MAX_MONETARY_VALUE;
        }
    };

    /**
     * Command base class for argument predicates that ensures a consistent
     * to-string representation.
     *
     * @param <T>
     *            argument type
     */
    public abstract static class ArgumentPredicate<T> implements Predicate<T> {

        /**
         * Should be overridden to provide a consistent toString representation
         * for predicates.
         *
         * @return a to string message describing the predicate precondition
         *         that failed and the argument
         */
        public abstract String getPredicateDescription();

        /**
         * Should be overridden to provide a consistent toString representation
         * for predicates.
         *
         * @return a to string message describing the predicate precondition
         *         that failed and the argument
         */
        @Override
        public String toString() {
            return getPredicateDescription();
        }
    }

    /**
     * Predicate that ensures that a provided input string has a minimum length
     * of {@code minLength}.
     *
     * @param minLength
     *            minimum length
     *
     * @return predicate
     */
    public static ArgumentPredicate<String> minLength(final int minLength) {

        return new ArgumentPredicate<String>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(String input) {
                return input.length() >= minLength;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("length at lest %s", minLength);
            }
        };
    }

    /**
     * Predicate that ensures that a provided input string has a maximum length
     * of {@code maxLength}.
     *
     * @param maxLength
     *            max length
     *
     * @return predicate
     */
    public static ArgumentPredicate<String> maxLength(final int maxLength) {

        return new ArgumentPredicate<String>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(String input) {
                return input.length() <= maxLength;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("length at most %s", maxLength);
            }
        };
    }

    /**
     * Predicate that ensures that a provided input string has a minimum length
     * of {@code minLength} and a maximum length of {@code maxLength}.
     *
     * @param minLength
     *            minimum length
     * @param maxLength
     *            maximum length.
     *
     * @return predicate
     */
    public static ArgumentPredicate<String> length(final int minLength,
                                                   final int maxLength) {

        return new ArgumentPredicate<String>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(String input) {
                return is(minLength(minLength), input)
                       && is(maxLength(maxLength), input);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("length between %s and %s", minLength,
                        maxLength);
            }
        };
    }

    /**
     * Predicate that ensures that a provided input numerical object has a
     * minimum value of of {@code minValue}.
     *
     * @param minValue
     *            minimum length
     *
     * @return predicate
     */
    public static ArgumentPredicate<Number> minValue(final int minValue) {

        return new ArgumentPredicate<Number>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(Number input) {
                return input.doubleValue() >= minValue;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("value at lest %s", minValue);
            }
        };
    }

    /**
     * Predicate that ensures that a provided input number has a maximum value
     * of {@code maxValue}.
     *
     * @param maxValue
     *            max value
     *
     * @return predicate
     */
    public static ArgumentPredicate<Number> maxValue(final int maxValue) {

        return new ArgumentPredicate<Number>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(Number input) {
                return input.doubleValue() <= maxValue;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("value at most %s", maxValue);
            }
        };
    }

    /**
     * Predicate that ensures that a provided input number has a minimum value
     * of {@code minValue} and a maximum value of {@code maxValue}.
     *
     * @param minValue
     *            minimum value
     * @param maxValue
     *            maximum value.
     *
     * @return predicate
     */
    public static ArgumentPredicate<Number> valueBetween(final int minValue,
                                                         final int maxValue) {

        return new ArgumentPredicate<Number>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(Number input) {
                return is(minValue(minValue), input)
                       && is(maxValue(maxValue), input);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("value between %s and %s", minValue,
                        maxValue);
            }
        };
    }

    /**
     * Predicate that ensures that a provided input BigDecimal has a maximum
     * scale of {@code maxScale}.
     *
     * @param maxScale
     *            maximum scale
     * @return predicate
     */
    public static ArgumentPredicate<BigDecimal> maxScale(final int maxScale) {

        return new ArgumentPredicate<BigDecimal>() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean apply(BigDecimal input) {
                return input.scale() <= maxScale;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getPredicateDescription() {
                return String.format("scale less or equal to %s", maxScale);
            }
        };
    }

    /**
     * Utility function enabling more fluent use of predicates. Applies the
     * provided input to the provided predicate and returns the result.
     *
     * @param predicate
     *            predicate to apply
     *
     * @param <T>
     *            predicate type
     *
     * @param input
     *            input
     *
     * @return boolean result of the predicate evaluation of the input.
     */
    public static <T> boolean is(Predicate<T> predicate, T input) {
        return predicate.apply(input);
    }

    /**
     * Utility function enabling more fluent use of predicates. Applies the
     * provided input to the provided predicate and negates the result.
     *
     * @param predicate
     *            predicate to apply
     *
     * @param <T>
     *            predicate type
     *
     * @param input
     *            input
     *
     * @return neated boolean result of the predicate evaluation of the input.
     */
    public static <T> boolean not(Predicate<T> predicate, T input) {
        return !is(predicate, input);
    }

}

