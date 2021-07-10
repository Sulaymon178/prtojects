package org.example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqStudent {
    private UUID id;
    private String firstName;
    private String lastName;
    private UUID groupId;
    private String serial;
    private String number;
}
