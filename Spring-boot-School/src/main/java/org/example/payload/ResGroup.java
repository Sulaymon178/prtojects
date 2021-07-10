package org.example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entitiy.enums.GroupStatus;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResGroup {
    private UUID id;
    private String name;
    private GroupStatus groupStatus;
}
