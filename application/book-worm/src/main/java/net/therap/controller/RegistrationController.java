package net.therap.controller;

import net.therap.domain.Area;
import net.therap.domain.User;
import net.therap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author shakhawat.hossain
 * @author rifatul.islam
 * @since 8/4/14
 */

@Controller
public class RegistrationController {
    private static final String REG_SUCCESS_MSG = "Congratulation! Your Registration is Complete. Please Login.";
    private static final String DEFAULT_USER_IMAGE_FILE = "/resources/images/user.png";
    @Autowired
    private UserService userService;

    @Autowired
    ServletContext servletContext;

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        return new ModelAndView("user/registration", "user", new User());
    }

    @RequestMapping (value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid @ModelAttribute ("user") User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:login");

        if (result.hasErrors()) {
            modelAndView.setViewName("user/registration");
            return modelAndView;
        }

        Area area = new Area();
        area.setAreaCode(Area.DEFAULT_AREA_CODE);
        user.setArea(area);

        if (user.getProfilePicture() == null) {
            user.setProfilePicture(getDefaultImageData(DEFAULT_USER_IMAGE_FILE));
        }

        userService.addUser(user);
        modelAndView.addObject("registrationSuccess", REG_SUCCESS_MSG);
        return modelAndView;
    }

    private byte[] getDefaultImageData(String filePath) {
        File file = new File(servletContext.getRealPath(filePath));

        byte[] imageData = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageData;
    }
}
