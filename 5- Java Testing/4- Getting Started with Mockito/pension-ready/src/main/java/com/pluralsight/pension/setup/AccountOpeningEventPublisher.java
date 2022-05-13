package com.pluralsight.pension.setup;

public interface AccountOpeningEventPublisher {

    void notify(String accountId);
}
