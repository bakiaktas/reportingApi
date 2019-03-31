package com.baktas.reportingapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReportResponse {
    private String status;

    private String message;

    private List<TransactionReport> response;
}
