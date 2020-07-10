package UTN.FRGP.TP_L5_GRUPO_1.Controllers;

import org.hibernate.SessionException;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AbstractController {
    protected final void redirectTo(HttpServletResponse response, HttpServletRequest request, String url) {
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", request.getContextPath() + "/" + url);
    }

    protected final void redirectTo(HttpServletResponse response, HttpServletRequest request, String url, Map<String, Object> parameters) {
        StringBuilder urlWithParameters = new StringBuilder(url).append("?");

        parameters.forEach((name, value) -> urlWithParameters.append(name).append("=").append(value).append("&"));

        redirectTo(response, request, urlWithParameters.toString());
    }

    protected final void revokeSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    protected final void validateIsAdministrator(HttpSession session) {
        if (!((Boolean) session.getAttribute("isAdministrator"))) {
            throw new SessionException(null);
        }
    }
}
