package com.hajer.Headphone.Dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class changePasswordRequest {
    @NonNull
    @NotEmpty(message = "paswword is empty !")
    @Size(min = 8,max = 16,message = "password must be betwen 8 and 16 !")
    private String currentPassword;

    @NonNull
    @NotEmpty(message = "paswword is empty !")
    @Size(min = 8,max = 16,message = "password must be betwen 8 and 16 !")
    private String newPassword;

    @NonNull
    @NotEmpty(message = "paswword is empty !")
    @Size(min = 8,max = 16,message = "password must be betwen 8 and 16 !")
    private String confirmPassword;
}
