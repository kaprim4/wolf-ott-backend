package com.wolfott.stream_mangement.specifications;

import com.wolfott.stream_mangement.exceptions.InvalidFilterException;
import com.wolfott.stream_mangement.models.Stream;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

@Component
public class StreamSpecification {
    public Specification<Stream> dynamic(Map<String, Object> filters) {
        return (Root<Stream> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
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
                    Class<?> fieldType = getFieldType(Stream.class, key);

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
