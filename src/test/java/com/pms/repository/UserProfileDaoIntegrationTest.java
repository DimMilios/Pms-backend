package com.pms.repository;

import com.pms.dao.UserProfileDao;
import com.pms.model.userprofile.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import java.util.Optional;

import static com.pms.model.Role.ADMIN;
import static com.pms.model.Role.USER;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserProfileDaoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserProfileDao userProfileDao;

    @Test
    public void whenFindByUsername_thenReturnUserProfile() {
        // given
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("test@test.com");
        userProfile.setUsername("test");
        userProfile.setPassword("123");
        userProfile.setRole(ADMIN);

        userProfileDao.save(userProfile);

        // when
        Optional<UserProfile> found = userProfileDao.findByUsername(userProfile.getUsername());

        // then
        assertThat(found.isPresent());

        found.map(UserProfile::getUsername)
                .map(username -> assertThat(username).isEqualToIgnoringCase("test"));

        found.map(UserProfile::getEmail)
                .map(email -> assertThat(email).isEqualToIgnoringCase("test@test.com"));

        found.map(UserProfile::getPassword)
                .map(password -> assertThat(password).isEqualToIgnoringCase("123"));

        found.map(UserProfile::getRole)
                .map(role -> assertThat(role).isEqualTo(ADMIN));

        found.map(UserProfile::getId)
                .map(id -> assertThat(id).isGreaterThanOrEqualTo(1L));

    }

    @Test
    public void whenFindByEmail_thenReturnUserProfile() {
        // given
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("test@test.com");
        userProfile.setUsername("test");
        userProfile.setPassword("123");
        userProfile.setRole(ADMIN);

        userProfileDao.save(userProfile);

        // when
        Optional<UserProfile> found = userProfileDao.findByEmail(userProfile.getEmail());

        // then
        assertThat(found.isPresent());

        found.map(UserProfile::getUsername)
                .map(username -> assertThat(username).isEqualToIgnoringCase("test"));

        found.map(UserProfile::getEmail)
                .map(email -> assertThat(email).isEqualToIgnoringCase("test@test.com"));

        found.map(UserProfile::getPassword)
                .map(password -> assertThat(password).isEqualToIgnoringCase("123"));

        found.map(UserProfile::getRole)
                .map(role -> assertThat(role).isEqualTo(ADMIN));

        found.map(UserProfile::getId)
                .map(id -> assertThat(id).isGreaterThanOrEqualTo(1L));

    }

    @Test
    public void whenSaveProfile_thenReturnNewUserProfile() {
        UserProfile profile = new UserProfile();
        profile.setEmail("new@test.com");
        profile.setUsername("user");
        profile.setPassword("123");
        profile.setRole(USER);

        UserProfile newProfile = userProfileDao.save(profile);

        assertThat(newProfile)
                .isNotNull();

        UserProfile savedProfile = entityManager.find(UserProfile.class, newProfile.getId());

        assertThat(newProfile)
                .isEqualToComparingFieldByFieldRecursively(savedProfile);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenSaveProfileAndUsernameAlreadyExists_thenThrowsException() {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("test@test.com");
        userProfile.setUsername("test");
        userProfile.setPassword("123");
        userProfile.setRole(ADMIN);

        userProfileDao.save(userProfile);

        UserProfile profile = new UserProfile();
        profile.setEmail("test1@test.com");
        profile.setUsername("test");
        profile.setPassword("123");
        profile.setRole(USER);

        userProfileDao.save(profile);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void whenSaveProfileAndEmailAlreadyExists_thenThrowsException() {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("test@test.com");
        userProfile.setUsername("test");
        userProfile.setPassword("123");
        userProfile.setRole(ADMIN);

        userProfileDao.save(userProfile);

        UserProfile profile = new UserProfile();
        profile.setEmail("test@test.com");
        profile.setUsername("test123");
        profile.setPassword("123");
        profile.setRole(USER);

        userProfileDao.save(profile);
    }
}
