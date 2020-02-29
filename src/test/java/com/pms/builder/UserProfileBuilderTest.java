package com.pms.builder;


import com.pms.model.Role;
import com.pms.model.userprofile.UserProfile;
import com.pms.model.userprofile.UserProfileBuilder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserProfileBuilderTest {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    private static final String regex = "^(?=.{3,60}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

    @Test
    public void whenBuildingAUserProfile_thenReturnsUserProfile() {
        UserProfile build = UserProfileBuilder.userProfile()
                .withUsername("george123")
                .withPassword("123")
                .withEmail("george123@test.com")
                .withRole("ADMIN")
                .build();

        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("george123@test.com");
        userProfile.setUsername("george123");
        userProfile.setPassword(passwordEncoder().encode("123"));
        userProfile.setRole(Role.ADMIN);

        Assertions.assertAll("Profiles should be equal",
                () -> assertThat(build.getId()).isEqualTo(userProfile.getId()),
                () -> assertThat(build.getUsername()).isEqualTo(userProfile.getUsername()),
                () -> assertThat(build.getEmail()).isEqualTo(userProfile.getEmail()),
                () -> assertThat(build.getRole()).isEqualTo(userProfile.getRole()),
                () -> assertThat(build.getPassword()).hasSize(userProfile.getPassword().length()));

    }

    @Test
    public void whenBuildingAndUsernameDoesNotMatchPattern_thenThrowsRunTimeException() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            UserProfileBuilder.userProfile()
                    .withUsername("")
                    .withPassword("123")
                    .withEmail("george123@test.com")
                    .withRole("ADMIN")
                    .build();
        });

        String expectedMessage = "Username is empty or wrong format";
        String actualMessage = exception.getMessage();

        assertThat(expectedMessage).isEqualTo(actualMessage);
    }
}
