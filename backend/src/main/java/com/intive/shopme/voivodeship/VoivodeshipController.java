package com.intive.shopme.voivodeship;

import com.intive.shopme.model.db.DbVoivodeship;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.intive.shopme.config.ApiUrl.VOIVODESHIPS;
import static com.intive.shopme.config.SwaggerApiInfoConfigurer.Operations.SUCCESS;

@RestController
@RequestMapping(value = VOIVODESHIPS)
@Api(value = "voivodeship", description = "REST API for voivodeship operations", tags = "Voivodeships")
class VoivodeshipController {

    private final VoivodeshipService service;

    public VoivodeshipController(VoivodeshipService service) {
        this.service = service;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS)
    })
    @ApiOperation(value = "Returns all voivodeships")
    public List<DbVoivodeship> getAllVoivodeships() {
        return service.getAll();
    }
}
