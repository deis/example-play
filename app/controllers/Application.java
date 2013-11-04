package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {

        String message = System.getenv("POWERED_BY"); 

        if (message == null) {
        	message = "Deis";
        }

        return ok("Powered by " + message);
    }

}
