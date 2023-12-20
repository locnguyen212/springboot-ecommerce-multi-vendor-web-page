package com.dodo.api.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

// Route mặc định cho swagger: http://localhost:9899/swagger-ui/index.html
@OpenAPIDefinition(
	//Áp dụng khai báo security cho toàn bộ controller, "name" phải giống như "name" trong phần @SecurityScheme
    security = {
            @SecurityRequirement(
                    name = "BearerAuthenticate"
            )
    }
)
//Khai báo dùng security scheme nào cho swagger, "name" rất quan trọng
@SecurityScheme(
	name = "BearerAuthenticate",
	description = "JWT Authenticate, Remember 'Bearer ' Is Auto Implement So No Need To Type It Out",
	scheme = "bearer",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {

}
