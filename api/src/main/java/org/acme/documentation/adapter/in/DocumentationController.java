package org.acme.documentation.adapter.in;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.acme.application.domain.exception.ApplicationNotFoundException;
import org.acme.documentation.app.DocumentationService;
import org.acme.documentation.domain.Documentation;
import org.acme.documentation.domain.DocumentationType;
import org.acme.documentation.domain.input.CreateDocumentationFile;
import org.acme.documentation.domain.ports.in.DocumentationRest;
import org.acme.roleGuard.RoleAllowedCustom;
import org.acme.storage.FileInput;
import org.acme.storage.FileNotFound;
import org.acme.storage.StorageFile;
import org.acme.user.domain.exception.UserNotFoundException;
import org.acme.user.domain.exception.VerificationTokenException;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import io.quarkus.security.Authenticated;
import io.quarkus.security.UnauthorizedException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/documentation")
@Authenticated
public class DocumentationController implements DocumentationRest {
    @Inject
    DocumentationService documentationService;

    @Override
    @Path("/application/{applicationId}")
    @GET
    @RoleAllowedCustom({ "admin", "editor", "visitor" })
    public List<Documentation> findDocumentationByAppId(@PathParam("applicationId") Long applicationId) {
        return documentationService.findDocumentationByAppId(applicationId);
    }

    @Override
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RoleAllowedCustom({ "admin", "editor" })
    public Documentation createDocumentation(@RestForm("image") FileUpload file, @RestForm Long applicationId,
            @RestForm DocumentationType type,
            @HeaderParam("Authorization") String authHeader) {

        try {
            String token = authHeader.substring("Bearer ".length());
            var doc = new CreateDocumentationFile(new FileInput(file.uploadedFile(), file.fileName()), type,
                    applicationId);
            return documentationService.createDocumentation(doc, token);
        } catch (IOException e) {
            throw new ServerErrorException(500);
        } catch (FileNotFound e) {
            throw new NotFoundException(e);
        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException(e);
        } catch (VerificationTokenException e) {
            throw new UnauthorizedException(e);
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @Path("/{filename}")
    @DELETE
    @RoleAllowedCustom({ "admin", "editor" })
    public Documentation deleteDocumentation(@PathParam("filename") String filename,
            @HeaderParam("Authorization") String authHeader) {
        try {
            String token = authHeader.substring("Bearer ".length());

            return documentationService.deleteDocumentation(filename, token);
        } catch (FileNotFound e) {
            throw new NotFoundException();
        } catch (ApplicationNotFoundException e) {
            throw new NotFoundException(e);
        } catch (VerificationTokenException e) {
            throw new UnauthorizedException(e);
        } catch (UserNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @Override
    @Path("/{filename}")
    @GET
    @RoleAllowedCustom({ "admin", "editor", "visitor" })
    // ilay maka anle fichier de ho affiche (ex:
    // http://localhost:8080/multipart/sary.jpeg)
    public Response getDocumentation(@PathParam("filename") String filename) {
        try {
            StorageFile documentation = documentationService.getDocumentation(filename);
            java.nio.file.Path filePath = java.nio.file.Paths.get(documentation.getUrl());
            return Response.ok(Files.readAllBytes(filePath)).type(Files.probeContentType(filePath)).build();
        } catch (FileNotFound e) {
            throw new NotFoundException();
        } catch (IOException e) {
            throw new ServerErrorException(500);
        }
    }

}
