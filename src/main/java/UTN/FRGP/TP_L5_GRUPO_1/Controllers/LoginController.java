package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import UTN.FRGP.TP_L5_GRUPO_1.Builders.AccountBuilder;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum;
import UTN.FRGP.TP_L5_GRUPO_1.Exceptions.AccountException;
import UTN.FRGP.TP_L5_GRUPO_1.Models.Account;
import UTN.FRGP.TP_L5_GRUPO_1.Models.BankAdministrator;
import UTN.FRGP.TP_L5_GRUPO_1.Models.User;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.AccountService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.LoginService;
import UTN.FRGP.TP_L5_GRUPO_1.Services.Repository.NotificationService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/")
@Controller
public class LoginController {

    @Autowired
    AbstractController abstractController;

    @RequestMapping(method = RequestMethod.GET)
    public String getLogin(HttpServletRequest request) {
        abstractController.revokeSession(request);

        return "/Unauthorized/Login/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void login(HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();
        String url = "accounts";

        try {
            User user = LoginService.authenticateUser(request.getParameter("email"), request.getParameter("password"));

            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("lastName", user.getLastName());
            request.getSession().setAttribute("isAdministrator", user instanceof BankAdministrator);

            String notifications = NotificationService.getNotifications(user.getId());

            if (notifications.length() > 0) {
                parameters.put("notifications", notifications);
            }
        } catch (LoginException e) {
            url = "";
            parameters.put("errorCode", ErrorCodeEnum.INVALID_CREDENTIALS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            abstractController.redirectTo(response, request, url, parameters);
        }
    }
}
