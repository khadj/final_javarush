package com.javarush.jira.profile.internal.web;

import com.javarush.jira.MatcherFactory;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.model.Contact;
import com.javarush.jira.profile.internal.model.Profile;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfileTestData {
    public static MatcherFactory.Matcher<Profile> PROFILE_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(Profile.class, "user");

    public static ProfileTo USER_PROFILE_TO = new ProfileTo(null,
            Set.of("assigned", "overdue", "deadline"),
            Set.of(new ContactTo("skype", "userSkype"),
                    new ContactTo("mobile", "+01234567890"),
                    new ContactTo("website", "user.com")));
    public static ProfileTo GUEST_PROFILE_EMPTY_TO = new ProfileTo(null,
            Set.of(),
            Set.of());

    public static ProfileTo getNewTo() {
        return new ProfileTo(null,
                Set.of("three_days_before_deadline", "two_days_before_deadline", "one_day_before_deadline"),
                Set.of(new ContactTo("tg", "guestTg")));
    }

    public static Profile getNew(long id) {
        Profile profile = new Profile();
        profile.setId(id);
        profile.setMailNotifications(14);
        profile.setContacts(Set.of(new Contact(id, "tg", "guestTg")));
        return profile;
    }

    public static ProfileTo getUpdatedTo() {
        return new ProfileTo(null,
                Set.of("assigned", "three_days_before_deadline", "two_days_before_deadline", "one_day_before_deadline", "deadline", "overdue"),
                Set.of(new ContactTo("skype", "newSkype"),
                        new ContactTo("mobile", "+380987654321"),
                        new ContactTo("website", "new.com"),
                        new ContactTo("github", "newGitHub"),
                        new ContactTo("tg", "newTg"),
                        new ContactTo("vk", "newVk"),
                        new ContactTo("linkedin", "newLinkedin")));
    }

    public static Profile getUpdated(long id) {
        Profile profile = new Profile();
        profile.setId(id);
        profile.setMailNotifications(63);
        profile.setContacts(Set.of(new Contact(id, "skype", "newSkype"),
                new Contact(id, "mobile", "+380987654321"),
                new Contact(id, "website", "new.com"),
                new Contact(id, "github", "newGitHub"),
                new Contact(id, "tg", "newTg"),
                new Contact(id, "vk", "newVk"),
                new Contact(id, "linkedin", "newLinkedin")));
        return profile;
    }

    public static ProfileTo getInvalidTo() {
        return new ProfileTo(null,
                Set.of(""),
                Set.of(new ContactTo("skype", "")));
    }

    public static ProfileTo getWithUnknownNotificationTo() {
        return new ProfileTo(null,
                Set.of("WrongNotification"),
                Collections.emptySet());
    }

    public static ProfileTo getWithUnknownContactTo() {
        return new ProfileTo(null,
                Collections.emptySet(),
                Set.of(new ContactTo("WrongContactCode", "contact")));
    }

    public static ProfileTo getWithContactHtmlUnsafeTo() {
        return new ProfileTo(null,
                Collections.emptySet(),
                Set.of(new ContactTo("tg", "<script>alert(123)</script>")));
    }


    public static final MatcherFactory.Matcher<ProfileTo> TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
            ProfileTo.class, "mailNotifications", "contacts"
    );

    public static final long PROFILE_ID = 1;
    public static final HashSet<String> MAIL_NOTIFICATIONS = new HashSet<>(List.of("assigned"));
    public static final HashSet<String> UPDATED_MAIL_NOTIFICATIONS = new HashSet<>(List.of("deadline", "overdue"));
    public static final ProfileTo profileTo = new ProfileTo(PROFILE_ID, MAIL_NOTIFICATIONS, null);
    public static final ProfileTo profileToForUpdate = new ProfileTo(PROFILE_ID, null, null);

    public static <T> String jsonWithMailNotifications(T profileTo, Set<String> mailNotifications) {
        return JsonUtil.writeAdditionProps(profileTo, "mail_notifications", mailNotifications);
    }
}
