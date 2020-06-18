package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String get(ModelMap modelMap) {
        modelMap.addAttribute("customers", CustomerService.getCustomers());

        return "/Authorized/Customers/index";
    }
}
