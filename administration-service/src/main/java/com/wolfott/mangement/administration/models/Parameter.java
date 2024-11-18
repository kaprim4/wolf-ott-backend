package com.wolfott.mangement.administration.models;

import com.wolfott.mangement.administration.converters.JsonConverter;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "`parameters`")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary Key for each setting entry

    @Column(name = "title")
    private String title;  // Optional: Title or name of the setting

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;  // Optional: Description or explanation of the setting

    @Column(name = "module_name")
    private String moduleName;  // Identifies the module the setting belongs to (e.g., 'user', 'email')

    @Column(name = "`key`", nullable = false, unique = true)
    private String key;  // Unique key for the setting (e.g., 'max_login_attempts')

    @Lob
    @Convert(converter = JsonConverter.class)
    @Column(name = "`value`", nullable = false, columnDefinition = "TEXT")
    private Object value;  // The actual value of the setting (can be a string, JSON, or serialized object)

    @Column(name = "`type`", nullable = false, length = 50)
    private String type;  // Type of the value: 'string', 'integer', 'boolean', 'list:string', 'object', etc.

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Timestamp when the setting was created

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();  // Timestamp when the setting was last updated

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

