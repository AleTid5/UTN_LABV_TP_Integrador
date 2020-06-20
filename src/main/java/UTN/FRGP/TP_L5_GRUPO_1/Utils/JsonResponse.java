package UTN.FRGP.TP_L5_GRUPO_1.Utils;

public class JsonResponse {
    private Boolean status = true;
    private Object data = null;

    public JsonResponse() {}

    public JsonResponse(Boolean status) {
        this.status = status;
    }

    public JsonResponse(Object data) {
        this.data = data;
    }

    public JsonResponse(Object data, Boolean status) {
        this.data = data;
        this.status = status;
    }
}
