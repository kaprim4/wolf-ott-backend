package com.wolfott.mangement.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@Table(name = "providers_streams")
public class ProviderStream {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provider_stream_seq")
    @SequenceGenerator(name = "provider_stream_seq", sequenceName = "provider_stream_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "stream_id")
    private Long streamId;

    @Lob
    @Column(name = "category_id", columnDefinition = "MEDIUMTEXT")
    private String categoryId;

    @Lob
    @Column(name = "category_array", columnDefinition = "MEDIUMTEXT")
    private String categoryArray;

    @Lob
    @Column(name = "stream_display_name", columnDefinition = "MEDIUMTEXT")
    private String streamDisplayName;

    @Lob
    @Column(name = "stream_icon", columnDefinition = "MEDIUMTEXT")
    private String streamIcon;

    @Column(name = "channel_id", length = 255)
    private String channelId;

    @Column(name = "added")
    private Integer added;

    @Column(name = "modified")
    private Integer modified;

    @Column(name = "type", length = 16)
    @ColumnDefault("'live'")
    private String type;

}
