package com.intive.shopme.registration;

import com.intive.shopme.common.ConvertibleController;
import com.intive.shopme.model.db.DbAddress;
import com.intive.shopme.model.db.DbInvoice;
import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.model.db.DbVoivodeship;
import com.intive.shopme.model.rest.Address;
import com.intive.shopme.model.rest.Invoice;
import com.intive.shopme.model.rest.Role;
import com.intive.shopme.model.rest.User;
import com.intive.shopme.model.rest.Voivodeship;
import com.intive.shopme.voivodeship.VoivodeshipValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.intive.shopme.config.ApiUrl.USERS;
import static com.intive.shopme.config.ErrorHandlingConfig.CONSTRAINTS_JSON_KEY;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.CREATED;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.NOT_FOUND;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;

@RestController
@RequestMapping(value = USERS)
@Api(value = "user", description = "REST API for users operations", tags = "Users")
class UserController extends ConvertibleController<DbUser, User> {

    private final UserService service;
    private final ValidInvoiceIfInvoiceRequestedValidator invoiceRequestedValidator;
    private final VoivodeshipValidator voivodeshipValidator;
    private final PasswordEncoder passwordEncoder;

    UserController(UserService service, ValidInvoiceIfInvoiceRequestedValidator invoiceRequestedValidator,
                   VoivodeshipValidator voivodeshipValidator, PasswordEncoder passwordEncoder) {
        super(DbUser.class, User.class);
        this.service = service;
        this.invoiceRequestedValidator = invoiceRequestedValidator;
        this.voivodeshipValidator = voivodeshipValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = CREATED),
    })
    @ApiOperation(value = "Saves new user", response = User.class)
    ResponseEntity<?> add(@Valid @RequestBody User user, Errors errors) {
        invoiceRequestedValidator.validate(user, errors);
        voivodeshipValidator.validate(user, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(Map.of(CONSTRAINTS_JSON_KEY, createErrorString(errors)), HttpStatus.BAD_REQUEST);
        }

        final var dbUser = convertToDbModel(user);
        dbUser.setId(UUID.randomUUID());
        dbUser.addRole(Role.USER);
        return ResponseEntity.ok(convertToView(service.createOrUpdate(dbUser)));
    }

    @GetMapping(value = "{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Returns user by id (temporary endpoint, please confirm in next REST API specification before production use)")
    User get(@PathVariable UUID id) {
        return convertToView(service.get(id));
    }

    @GetMapping(value = "/email={email}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 404, message = NOT_FOUND)
    })
    @ApiOperation(value = "Search if user with specified email exist in database")
    boolean getByEmail(@PathVariable String email) {
        return service.findIfUserExist(email.toLowerCase());
    }

    private static String createErrorString(Errors errors) {
        return errors.getAllErrors().stream()
                .map(ObjectError::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    protected User convertToView(DbUser dbUser) {
        final var result = new User();
        result.setId(dbUser.getId());
        result.setName(dbUser.getName());
        result.setSurname(dbUser.getSurname());
        result.setEmail(dbUser.getEmail());
        result.setPhoneNumber(dbUser.getPhoneNumber());
        result.setBankAccount(dbUser.getBankAccount());
        result.setRoles(dbUser.getRoles());
        if (dbUser.getAddress() != null) {
            result.setAddress(genericConvert(dbUser.getAddress(), Address.class));
        }
        if (dbUser.getVoivodeship() != null) {
            result.setVoivodeship(genericConvert(dbUser.getVoivodeship(), Voivodeship.class));
        }
        result.setInvoiceRequest(dbUser.getInvoiceRequest());
        if (dbUser.getVoivodeship() != null) {
            result.setInvoice(genericConvert(dbUser.getInvoice(), Invoice.class));
        }
        return result;
    }

    @Override
    protected DbUser convertToDbModel(User user) {
        final var result = new DbUser();
        result.setId(user.getId());
        result.setName(user.getName());
        result.setSurname(user.getSurname());
        result.setEmail(user.getEmail().toLowerCase());
        result.setPassword(passwordEncoder.encode(user.getPassword()));
        result.setPhoneNumber(user.getPhoneNumber());
        result.setBankAccount(user.getBankAccount());
        result.setRoles(user.getRoles());
        if (user.getAddress() != null) {
            result.setAddress(genericConvert(user.getAddress(), DbAddress.class));
        }
        if (user.getVoivodeship() != null) {
            result.setVoivodeship(genericConvert(user.getVoivodeship(), DbVoivodeship.class));
        }
        result.setInvoiceRequest(user.getInvoiceRequest());
        if (user.getVoivodeship() != null) {
            result.setInvoice(genericConvert(user.getInvoice(), DbInvoice.class));
        }
        return result;
    }
}
