package com.pms.middleware;

import com.pms.model.userprofile.UserProfile;

public abstract class UserProfileMiddleware {

    private UserProfileMiddleware next;
    private UserProfile userProfile;

    public UserProfileMiddleware(UserProfile userProfile) {
        this.userProfile = userProfile;
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
    public abstract boolean isValid(UserProfile userProfile);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(UserProfile userProfile) {
        if (next != null) {
            return next.isValid(userProfile);
        }
        return false;
    }
}
