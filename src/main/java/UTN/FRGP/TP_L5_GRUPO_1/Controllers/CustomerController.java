package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.UserException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Customer;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.CustomerService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.LocationService;
import UTN.FRGP.TP_L5_GRUPO_1.Utils.Helper;
import com.google.gson.Gson;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String createCustomer(ModelMap modelMap) {
        modelMap.addAttribute("countries", LocationService.getCountries());

        return "/Authorized/Customers/add";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void saveCustomer(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "customers";

        try {
            Customer customer = Helper.buildCustomerFromRequest(request);
            customer.setPassword(request.getParameter("dni"));

            CustomerService.saveCustomer(customer);
            parameters.put("successCode", SuccessCodeEnum.CUSTOMER_CREATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCodeEnum.DUPLICATED_CUSTOMER);
            url += "/add";
        } catch (UserException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_" + e.getField().toString()));
            url += "/add";
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer userId, ModelMap modelMap) {
        Customer customer = CustomerService.getCustomerById(userId);
        modelMap.addAttribute("customer", customer);
        modelMap.addAttribute("countries", LocationService.getCountries());
        modelMap.addAttribute("provinces", LocationService.getProvinces(customer.getLocality()));
        modelMap.addAttribute("localities", LocationService.getLocalities(customer.getLocality()));

        return "/Authorized/Customers/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{id}")
    public void updateCustomer(@PathVariable("id") Integer userId, HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "customers";

        try {
            Customer customer = Helper.buildCustomerFromRequest(request, CustomerService.getCustomerById(userId));
            customer.setId(userId);

            CustomerService.updateCustomer(customer);
            parameters.put("successCode", SuccessCodeEnum.CUSTOMER_UPDATED);
        } catch (ConstraintViolationException e) {
            parameters.put("errorCode", ErrorCodeEnum.DUPLICATED_CUSTOMER);
            url += "/edit/" + userId;
        } catch (UserException e) {
            parameters.put("errorCode", ErrorCodeEnum.valueOf("INVALID_" + e.getField().toString()));
            url += "/edit/" + userId;
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteCustomer(@PathVariable("id") Integer userId) {
        return new Gson().toJson(CustomerService.removeCustomer(CustomerService.getCustomerById(userId)));
    }
}
