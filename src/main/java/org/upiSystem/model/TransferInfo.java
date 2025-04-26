package org.upiSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferInfo {
    private String transferFrom;
    private String transferTo;
    private Long amount;
    private String accountFromNumber;
    private String accountToNumber;
}
