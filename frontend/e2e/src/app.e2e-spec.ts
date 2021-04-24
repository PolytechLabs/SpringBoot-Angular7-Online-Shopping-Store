import { AppPage } from './app.po';
import { RegistPage} from "./regist.po";
import {browser, by, element} from "protractor";
import {AuthenticationPageObject} from "./authentification.page";
import {LoginPageObject} from "./login";
import {CartPageObject} from "./cart.po";

describe('workspace-project App', () => {
  let page: AppPage;
  let registPage: RegistPage;
  let cartPage: CartPageObject;
  let loginPage: LoginPageObject;
  let pageObject = new RegistPage();
  let authPageObject = new AuthenticationPageObject();

  beforeEach(() => {
    browser.waitForAngularEnabled(false);
    page = new AppPage();
    cartPage = new CartPageObject();
    registPage = new RegistPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Get Whatever You Want!');
  });

  it('should get the register page', () => {
    authPageObject.goToRegisterPage();
    expect(pageObject.getTitle()).toEqual('Sign Up');
  });

  //Проверка возможности регистрации
  it('should validate the registration', () => {
    authPageObject.goToLoginPage();
    authPageObject.goToRegisterPage();
    pageObject.setEmail("email@email");
    pageObject.setName("Name");
    pageObject.setPassword("passw");
    pageObject.setPhoneInput("1234");
    pageObject.setAddressInput("address");
    pageObject.submitForm();
    expect(pageObject.formIsValid()).toEqual(true);
    authPageObject.goToLoginPage();
    expect(page.getTitleText()).toEqual('Sign In');
  });

  //Пользователь не может оформить заказ, если он не авторизован
  it('should validate that user is unauthentificate and cant create order', () => {
    page.goToProduct()
    page.submitForm()
    page.goToCart()
    cartPage.submitStuff()
    expect(page.getTitleText()).toEqual('Sign In');
  });

});
