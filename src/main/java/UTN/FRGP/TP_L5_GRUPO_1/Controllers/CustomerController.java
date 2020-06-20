package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCode;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCode;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.LocationService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.Helper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/customers")
@Controller
public class CustomerController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET)
    public String customerList(ModelMap modelMap) {
        modelMap.addAttribute("customers", CustomerService.getCustomers());

        return "/Authorized/Customers/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String createCustomerForm(ModelMap modelMap) {
        modelMap.addAttribute("countries", LocationService.getCountries());

        return "/Authorized/Customers/add";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void createCustomer(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "customers";

        try {
            Customer customer = Helper.buildCustomerFromRequest(request);
            customer.setPassword(request.getParameter("dni"));

            CustomerService.saveCustomer(customer);
            parameters.put("successCode", SuccessCode.CUSTOMER_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCode.DUPLICATED_CUSTOMER);
            url += "/add";
        } catch (UserException e) {
            parameters.put("errorCode", ErrorCode.valueOf("INVALID_" + e.getField().toString()));
            url += "/add";
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit}")
    public String editCustomerForm(ModelMap modelMap) {
        modelMap.addAttribute("countries", LocationService.getCountries());

        return "/Authorized/Customers/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public void editCustomer(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "customers";

        try {
            Customer customer = Helper.buildCustomerFromRequest(request);

            CustomerService.saveCustomer(customer);
            parameters.put("successCode", SuccessCode.CUSTOMER_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCode.DUPLICATED_CUSTOMER);
            url += "/add";
        } catch (UserException e) {
            parameters.put("errorCode", ErrorCode.valueOf("INVALID_" + e.getField().toString()));
            url += "/add";
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteCustomer(ModelMap modelMap) {
        return customerList(modelMap);
    }
}
