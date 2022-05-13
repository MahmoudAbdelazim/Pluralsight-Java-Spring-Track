package com.pluralsight.pension.withdrawal;

import java.time.LocalDateTime;

public class AccountClosingResponse {

    AccountClosingStatus status;
    LocalDateTime processingDate;

    public AccountClosingResponse(AccountClosingStatus status, LocalDateTime processingDate) {
        this.status = status;
        this.processingDate = processingDate;
    }

    public AccountClosingStatus getStatus() {
        return status;
    }

    public void setStatus(AccountClosingStatus status) {
        this.status = status;
    }

    public LocalDateTime getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(LocalDateTime processingDate) {
        this.processingDate = processingDate;
    }
}
