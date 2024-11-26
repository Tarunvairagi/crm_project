package com.crm.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
        private Long id;

        @NotBlank
        @Size(min = 3, message = "at least 3 character is required")
        private String name;

        @Email
        private String emailId;

        @NotBlank
        @Size(min = 10,max = 10, message = "Should be 10 number is required")
        private String mobile;
}
