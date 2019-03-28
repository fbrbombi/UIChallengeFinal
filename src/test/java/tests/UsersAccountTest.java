package tests;

import helpers.EcofoodFacade;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UsersAccountTest extends Hooks {


    @Test
    public void successfulRegister(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.goToRegisterPage();
        ecofoodFacade.fillRegisterForm();
        String message = ecofoodFacade.getRegisterResponse();
        assertThat("User Created", message, equalTo("Usuario creado"));
    }

    @Test
    public void successfulLogin(Method method) {

        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.goToLoginPage();
        ecofoodFacade.fillLoginForm();
        String response = ecofoodFacade.verifyLoginResponse();
        assertThat("The user logged in", response, equalTo("Logout"));
    }

    @Test
    public void unsuccessfulLogin(Method method) {

        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.goToLoginPage();
        ecofoodFacade.fillLoginFormWithIncorrectData();
        String response = ecofoodFacade.verifyUnsuccessfulLogin();
        assertThat("The user logged in", response, equalTo("¡HA OCURRIDO UN ERROR!"));
    }

    @Test
    public void logout(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.goToLoginPage();
        ecofoodFacade.fillLoginForm();
        ecofoodFacade.getLoggedOut();
        String response = ecofoodFacade.verifyLoginResponse();
        assertThat("The user logged in", response, equalTo("Registrarse"));
    }

}
