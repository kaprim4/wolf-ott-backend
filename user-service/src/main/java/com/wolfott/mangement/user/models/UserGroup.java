package com.wolfott.mangement.user.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "users_groups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "is_admin")
    @ColumnDefault("false")
    private Boolean isAdmin;

    @Column(name = "is_reseller")
    private Boolean isReseller;

    @Column(name = "total_allowed_gen_trials")
    private Integer totalAllowedGenTrials;

    @Column(name = "total_allowed_gen_in")
    private String totalAllowedGenIn;

    @Column(name = "delete_users")
    @ColumnDefault("false")
    private Boolean deleteUsers;

    @Column(name = "allowed_pages")
    private String allowedPages;

    @Column(name = "can_delete")
    private Boolean canDelete;

    @Column(name = "create_sub_resellers")
    @ColumnDefault("false")
    private Boolean createSubResellers;

    @Column(name = "create_sub_resellers_price")
    private Float createSubResellersPrice;

    @Column(name = "reseller_client_connection_logs")
    private Boolean resellerClientConnectionLogs;

    @Column(name = "can_view_vod")
    private Boolean canViewVod;

    @Column(name = "allow_download")
    private Boolean allowDownload;

    @Column(name = "minimum_trial_credits")
    private Integer minimumTrialCredits;

    @Column(name = "allow_restrictions")
    private Boolean allowRestrictions;

    @Column(name = "allow_change_username")
    private Boolean allowChangeUsername;

    @Column(name = "allow_change_password")
    private Boolean allowChangePassword;

    @Column(name = "minimum_username_length")
    private Integer minimumUsernameLength;

    @Column(name = "minimum_password_length")
    private Integer minimumPasswordLength;

    @Column(name = "allow_change_bouquets")
    @ColumnDefault("false")
    private Boolean allowChangeBouquets;

    @Lob
    @Column(name = "notice_html")
    private String noticeHtml;

    @Lob
    @Column(name = "subresellers")
    private String subResellers;

    // Add getters and setters if needed
}
