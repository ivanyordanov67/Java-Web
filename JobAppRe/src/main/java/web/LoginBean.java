package web;

import domain.models.binding.LoginBindingModel;
import domain.models.services.UserServiceModel;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {

    private LoginBindingModel model;
    private UserService userService;
    private ModelMapper modelMapper;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.model=new LoginBindingModel();
    }

    public void login(){

        String hashPassword = this.userService.hashPassword(this.model.getPassword());

        UserServiceModel user =this.userService.getByUsernameAndId(this.model.getUsername(),hashPassword);

        if (user==null){
            this.redirect("register");
        }

       Map<String, Object> sessionMap =
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        sessionMap.put("username", user.getUsername());
        this.redirect("/home");

    }

    public LoginBindingModel getModel() {
        return model;
    }

    public void setModel(LoginBindingModel model) {
        this.model = model;
    }
}
