package com.wolfott.mangement.user.specifications;

import com.wolfott.mangement.user.exceptions.InvalidFilterException;
import com.wolfott.mangement.user.models.User;
import jakarta.persistence.Transient;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

@Component
public class UserSpecification {

    // Define fields to search and excluded fields
    private static final Set<String> EXCLUDED_FIELDS = Set.of("id", "password", "timestampLastLogin", "timestampDateRegistered");

    public static Specification<User> hasMemberId(Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ownerId"), userId);
    }

    public Specification<User> dynamic(Map<String, Object> filters) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            // Define a set of keys to ignore
            Set<String> pageableFields = Set.of("page", "size", "sort");

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                // Skip pageable fields
                if (pageableFields.contains(key)) {
                    continue;
                }

                if (value != null && !value.toString().isEmpty()) {
                    Predicate filterPredicate;
                    Class<?> fieldType = getFieldType(User.class, key);

                    if (fieldType != null) {
                        if (fieldType.equals(String.class)) {
                            // String field: apply LIKE
                            filterPredicate = builder.like(builder.lower(root.get(key).as(String.class)), "%" + value.toString().toLowerCase() + "%");
                        } else if (Number.class.isAssignableFrom(fieldType)) {
                            // Numeric field: apply EQUALS
                            filterPredicate = builder.equal(root.get(key), convertToNumber(value, fieldType));
                        } else if (fieldType.equals(LocalDate.class)) {
                            // LocalDate field: apply EQUALS
                            filterPredicate = builder.equal(root.get(key), convertToDate(value));
                        } else {
                            // Default case for unknown types: apply EQUALS
                            filterPredicate = builder.equal(root.get(key), value.toString());
                        }

                        predicate = builder.and(predicate, filterPredicate);
                    } else {
                        throw new InvalidFilterException("Invalid field name: " + key);
                    }
                }
            }
            return predicate;
        };
    }

    public Specification<User> search(String keyword) {
        return (root, query, builder) -> {
            Predicate predicate = builder.disjunction(); // OR

            // Iterate over all declared fields of the User class
            for (Field field : User.class.getDeclaredFields()) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();

                // Skip excluded fields and transient fields
                if (EXCLUDED_FIELDS.contains(fieldName) || field.isAnnotationPresent(Transient.class)) {
                    continue;
                }

                Path<?> fieldPath = root.get(fieldName);

                if (String.class.equals(fieldType)) {
                    // Create a predicate for String fields
                    Predicate fieldPredicate = builder.like(
                            builder.lower(fieldPath.as(String.class)),
                            "%" + keyword.toLowerCase() + "%"
                    );
                    predicate = builder.or(predicate, fieldPredicate);

                } else if (Number.class.isAssignableFrom(fieldType)) {
                    // Create a predicate for Numeric fields
                    Predicate fieldPredicate = buildNumericPredicate(fieldPath, keyword, fieldType, builder);
                    if (fieldPredicate != null) {
                        predicate = builder.or(predicate, fieldPredicate);
                    }

                } else if (Boolean.class.equals(fieldType)) {
                    // Create a predicate for Boolean fields
                    Predicate fieldPredicate = builder.equal(fieldPath, Boolean.parseBoolean(keyword));
                    predicate = builder.or(predicate, fieldPredicate);
                }
            }

            return predicate;
        };
    }

    private Predicate buildNumericPredicate(Path<?> fieldPath, String keyword, Class<?> fieldType, CriteriaBuilder builder) {
        try {
            Number numberValue = convertToNumber(keyword, fieldType);
            return builder.equal(fieldPath, numberValue);
        } catch (NumberFormatException e) {
            // Return null if conversion fails, meaning no predicate for invalid numbers
            return null;
        }
    }

    private Number convertToNumber(String value, Class<?> fieldType) {
        if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
            return Integer.parseInt(value);
        } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
            return Long.parseLong(value);
        } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
            return Double.parseDouble(value);
        } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
            return Float.parseFloat(value);
        }
        throw new IllegalArgumentException("Unsupported number type: " + fieldType);
    }

    private Class<?> getFieldType(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            return field.getType();
        } catch (NoSuchFieldException e) {
            return null; // Field not found; return null or handle appropriately
        }
    }

    private Number convertToNumber(Object value, Class<?> fieldType) {
        try {
            if (fieldType.equals(Integer.class)) {
                return Integer.parseInt(value.toString());
            } else if (fieldType.equals(Long.class)) {
                return Long.parseLong(value.toString());
            } else if (fieldType.equals(Double.class)) {
                return Double.parseDouble(value.toString());
            } else if (fieldType.equals(Float.class)) {
                return Float.parseFloat(value.toString());
            }
        } catch (NumberFormatException e) {
            throw new InvalidFilterException("Invalid number format for field: " + fieldType.getSimpleName());
        }
        throw new InvalidFilterException("Unsupported number type: " + fieldType.getSimpleName());
    }

    private LocalDate convertToDate(Object value) {
        try {
            return LocalDate.parse(value.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidFilterException("Invalid date format: " + value);
        }
    }
}
