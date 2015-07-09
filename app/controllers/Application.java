package controllers;

import play.*;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

import models.Image;
import models.Image.Info;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import java.io.File;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public Result get() {
		List<Info> infos = new ArrayList<Info>();
		infos.add(new Info("たけし"));
		infos.add(new Info("さやか"));
		infos.add(new Info("けんじ"));
		Image image = new Image(1, "Aチーム", infos);

		return ok(Json.toJson(image));
	}
    
    public Result upload() {
    	MultipartFormData body = request().body().asMultipartFormData();
    	FilePart picture = body.getFile("picture");
    	if (picture != null) {
    		String fileName = picture.getFilename();
    		String contentType = picture.getContentType();
    		File file = picture.getFile();
    		if ( contentType.equals("image/jpeg") ) {
    			String fullPath = Play.application().path().getPath() + "/public/images/upload/";
    			file.renameTo(new File(fullPath, fileName));
    		}
    		return ok("File uploaded");
    	} else {
    		flash("error", "Missing file");
    		return redirect(routes.Application.index());
   		}
	}

}
