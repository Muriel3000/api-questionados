package ar.com.ada.api.questionados.models.response;

public class GenericResponse {
    
    public boolean isOk;
    public Integer id;
    public String message;

    public GenericResponse(){};
    
    public GenericResponse(boolean isOk, Integer id){
        this.isOk = isOk;
        this.id = id;
    }

}
