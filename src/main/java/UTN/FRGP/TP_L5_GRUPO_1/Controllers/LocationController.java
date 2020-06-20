package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.LocationService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.JsonResponse;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/locations")
@Controller
public class LocationController {

    @RequestMapping(method = RequestMethod.GET, value = "/provinces/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String provinceList(@PathVariable("id") int provinceId) {
        return new Gson().toJson(new JsonResponse(LocationService.getProvinces(provinceId)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/localities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String localityList(@PathVariable("id") int localityId) {
        return new Gson().toJson(new JsonResponse(LocationService.getLocalities(localityId)));
    }
}
