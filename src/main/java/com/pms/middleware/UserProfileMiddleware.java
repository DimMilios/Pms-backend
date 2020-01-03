package com.pms.middleware;

import com.pms.dao.UserProfileDao;
import com.pms.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserProfileMiddleware {

    private UserProfileMiddleware next;

    protected UserProfileDao userProfileDao;

    @Autowired
    public UserProfileMiddleware(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    /**
     * Builds chains of middleware objects.
     */
    public UserProfileMiddleware linkWith(UserProfileMiddleware next) {
        this.next = next;
        return next;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(UserProfile userProfile);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(UserProfile userProfile) {
        if (next == null) {
            return true;
        }
        return next.check(userProfile);
    }
}
