package org.elevator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private int sourceFloor;
    private int destinationFloor;
}
