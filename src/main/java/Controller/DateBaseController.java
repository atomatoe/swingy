package Controller;

public class DateBaseController {

    private static DateBaseController instance;

    private DateBaseController() { }

    public static DateBaseController getInstance() {
        if(instance == null) {
            instance = new DateBaseController();
        }
        return instance;
    }

}
