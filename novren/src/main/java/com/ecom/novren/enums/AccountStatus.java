package com.ecom.novren.enums;

public enum AccountStatus {

    PENDING_VERIFICATION,   // Waiting for approval or verification
    ACTIVE,                 // Account is active and usable
    SUSPENDED,              // Temporarily disabled due to violations or issues
    DEACTIVATED,            // Account is deactivated by user or admin
    BANNED,                 // Permanently banned due to violation
    CLOSED                  // User closed the account
}
