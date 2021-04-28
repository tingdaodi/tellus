package com.tellus.permission.oauth2.support;

import com.tellus.support.enums.DisplayModeEnum;
import com.tellus.support.enums.ParamMethodEnum;
import com.tellus.support.enums.UserStatusEnum;
import com.tellus.support.enums.UserTypeEnum;
import com.tellus.support.enums.basic.Gender;
import com.tellus.support.enums.basic.Language;
import com.tellus.support.interfaces.ISubordinate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 自定义: 用户认证实体
 *
 * @author Roy
 * @date 2020/7/2 11:02
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomizeUserDetails implements UserDetails, CredentialsContainer, Serializable {
    private static final long serialVersionUID = 4376097749880009749L;

    private Set<? extends GrantedAuthority> authorities;
    private Set<Field> fields;
    private Set<Role> roles;
    private Set<Resource> resources;
    private Set<Group> groups;
    private Set<Platform> platforms;
    private Details details;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    // ~ Constructors
    // ========================================================================================================


    // ~ Override Methods
    // ========================================================================================================

    @Override
    public void eraseCredentials() {
        this.details.setPassword(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.details.getPassword();
    }

    @Override
    public String getUsername() {
        return this.details.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.details.getStatus(), UserStatusEnum.NORMAL);
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof CustomizeUserDetails) {
            return this.details.username.equals(((CustomizeUserDetails) rhs).details.username);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.details.username.hashCode();
    }

    // ~ Users Builder
    // ========================================================================================================

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder {
        private Set<? extends GrantedAuthority> authorities;
        private Set<Field> fields;
        private Set<Role> roles;
        private Set<Resource> resources;
        private Set<Group> groups;
        private Set<Platform> platforms;
        private Details details;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder = password -> password;

        private UserBuilder() {
        }

        public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = Collections.unmodifiableSet(CustomizeUserDetails.sortAuthorities(authorities));
            return this;
        }

        public UserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        public UserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public UserBuilder fields(Collection<Field> fields) {
            this.fields = new HashSet<>(fields);
            return this;
        }

        public UserBuilder roles(Collection<Role> roles) {
            this.roles = new HashSet<>(roles);

            List<GrantedAuthority> authorities = roles
                    .stream()
                    .map(role -> {
                        String value = role.role;
                        Assert.isTrue(!value.startsWith("ROLE_"), () -> value
                                + " cannot start with ROLE_ (it is automatically added)");
                        return new SimpleGrantedAuthority("ROLE_" + value);
                    }).collect(Collectors.toList());

            return authorities(authorities);
        }

        public UserBuilder resources(Collection<Resource> resources) {
            this.resources = new HashSet<>(resources);
            return this;
        }

        public UserBuilder groups(Collection<Group> groups) {
            this.groups = new HashSet<>(groups);
            return this;
        }

        public UserBuilder platforms(Collection<Platform> platforms) {
            this.platforms = new HashSet<>(platforms);
            return this;
        }

        public UserBuilder details(CustomizeUserDetails.Details details) {
            Assert.notNull(details.getUsername(), "username cannot be null");
            Assert.notNull(details.getPassword(), "password cannot be null");
            this.details = details;
            return this;
        }

        public UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails builder() {
            String encodedPassword = this.passwordEncoder.apply(this.details.password);
            return new CustomizeUserDetails(authorities, fields, roles, resources,
                    groups, platforms, details, !accountExpired, !accountLocked,
                    !credentialsExpired, !disabled);
        }
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set.
            // If the authority is null, it is a custom authority and should precede
            // others.
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    // ~ Inner Classes
    // ========================================================================================================

    @Data
    @lombok.Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Field implements Serializable {
        private static final long serialVersionUID = 4809414783194935101L;

        private Integer id;
        private Integer resourceId;
        private ParamMethodEnum method;
        private String label;
        private String name;
        private String type;
        private DisplayModeEnum displayMode;
    }

    @Data
    @lombok.Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Role implements ISubordinate, Serializable {
        private static final long serialVersionUID = -6435490671158722109L;

        private Integer id;
        private String name;
        private String role;
        private Integer parentId;
    }

    @Data
    @lombok.Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Resource implements Serializable {
        private static final long serialVersionUID = 4809414783194935101L;

        private Integer id;
        private String name;
        private String value;
    }

    @Data
    @lombok.Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Group implements ISubordinate, Serializable {
        private static final long serialVersionUID = 4809414783194935101L;

        private Integer groupId;
        private String groupName;
        private String groupValue;
        private Integer groupParentId;

        @Override
        public Integer getParentId() {
            return groupId;
        }

        @Override
        public Integer getId() {
            return groupParentId;
        }
    }

    @Data
    @lombok.Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Platform implements Serializable {
        private static final long serialVersionUID = 4809414783194935101L;

        private Integer platformId;
        private String platformName;
        private String platformCode;
        private Integer platformEnabled;
    }

    @Data
    @lombok.Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Details implements Serializable {
        private static final long serialVersionUID = 6755145433458706214L;

        private Integer id;
        private String username;
        private String password;
        private String nickname;
        private Gender gender;
        private UserTypeEnum userType;
        private UserStatusEnum status;
        private String email;
        private String phone;
        private String officePhone;
        private LocalDateTime birthday;
        private String avatar;
        private Language language;
        private String ipAddress;
        private LocalDateTime lastLoginTime;
        private String lastLoginIp;
        private String remarks;
        private String creator;
        private LocalDateTime createdAt;
        private String updater;
        private LocalDateTime updatedAt;
    }

}

