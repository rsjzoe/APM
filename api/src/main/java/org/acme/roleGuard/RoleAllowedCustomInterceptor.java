package org.acme.roleGuard;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ForbiddenException;
import java.util.Arrays;

import org.acme.user.app.UserService;
import org.acme.user.domain.UserOutput;

@RoleAllowedCustom({})
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class RoleAllowedCustomInterceptor {

    @Inject
    UserService userService;

    @Context
    HttpHeaders headers;

    @AroundInvoke
    public Object checkRole(InvocationContext context) throws Exception {
        RoleAllowedCustom annotation = context.getMethod().getAnnotation(RoleAllowedCustom.class);
        if (annotation == null) {
            annotation = context.getTarget().getClass().getAnnotation(RoleAllowedCustom.class);
        }

        if (annotation != null) {
            String[] allowedRoles = annotation.value();
            String token = headers.getHeaderString("Authorization");

            if (token == null || !token.startsWith("Bearer ")) {
                throw new ForbiddenException("Token non fourni ou invalide");
            }

            token = token.replace("Bearer ", "").trim();
            UserOutput user = userService.me(token);

            if (user == null || !Arrays.asList(allowedRoles).contains(user.getRole().name())) {
                throw new ForbiddenException("Accès refusé !");
            }
        }

        return context.proceed();
    }
}
