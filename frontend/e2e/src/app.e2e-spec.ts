import { AppPage } from './app.po';
import { RegistPage} from "./regist.po";
import {browser, by, element} from "protractor";
import {AuthenticationPageObject} from "./authentification.page";
import {LoginPageObject} from "./login";
import {CartPageObject} from "./cart.po";
import {ProductPageObject} from "./product.po"

describe('workspace-project App', () => {
  let page: AppPage;
  let registPage: RegistPage;
  let cartPage: CartPageObject;
  let loginPage: LoginPageObject;
  let productPage: ProductPageObject;
  let pageObject = new RegistPage();
  let authPageObject = new AuthenticationPageObject();

  beforeEach(() => {
    browser.waitForAngularEnabled();
    page = new AppPage();
    loginPage = new LoginPageObject();
    productPage = new ProductPageObject();
    cartPage = new CartPageObject();
    registPage = new RegistPage();
  });

  //Авторизация пользователя
  it('should validate that user authentication', () => {
    page.navigateTo()
    page.toLogin()
    loginPage.setEmail("email@emailru");
    loginPage.setPassword("123");
    loginPage.submitForm();
    expect(page.getTitleText()).toEqual('Get Whatever You Want!');
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Get Whatever You Want!');
  });

  /*//Проверка возможности регистрации
  it('should validate the registration', () => {
    authPageObject.goToLoginPage();
    authPageObject.goToRegisterPage();
    pageObject.setEmail("email@e");
    pageObject.setName("Name");
    pageObject.setPassword("passw");
    pageObject.setPhoneInput("1234");
    pageObject.setAddressInput("address");
    pageObject.submitForm();
    expect(pageObject.formIsValid()).toEqual(true);
   // authPageObject.goToLoginPage();
    expect(page.getTitleText()).toEqual('Sign In');
  });*/

  //Нельзя создать еще один аккаунт для уже известной почты
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
    expect(page.getTitleText()).toEqual('Sign Up');
  });

  //Пользователь не может оформить заказ, если он не авторизован
  it('should validate that user is unauthentificate and cant create order', () => {
    page.goToProduct()
    page.submitForm()
    page.goToCart()
    cartPage.submitStuff()
    expect(page.getTitleText()).toEqual('Sign In');
  });

  //Проверка возможности оформить заказ
  it('should validate the possibility of order creation', () => {
    authPageObject.goToLoginPage();
    loginPage.setEmail("email@emailru");
    loginPage.setPassword("123");
    loginPage.submitForm();
    page.getIt()
    page.submitForm()
    cartPage.submitStuffs()
    expect(page.getTitleText()).toEqual('Get Whatever You Want!');
  });

  //Проверка правильности пароля
  it('should show that password or email is invalid', () => {
    authPageObject.goToLoginPage();
    loginPage.setEmail("email@emailru");
    loginPage.setPassword("12311");
    loginPage.submitForm();
    expect(loginPage.getTitleText()).toEqual('Invalid username and password.');
  });

  //Проеврка иизменения данных товара менеджером
  it('should check ability of manager to change data', () => {
    authPageObject.goToLoginPage();
    loginPage.setManager();
    productPage.navigateToEditPage();
    productPage.setNewDescription("New description");
    productPage.submitForm();
    expect(productPage.getTitleText()).toEqual('Products');
  });

  //Проеврка на возможность отменить заказ менеджером
  it('should check ability of manager to change data', () => {
    authPageObject.goToLoginPage();
    loginPage.setManager();

    expect(loginPage.getTitleText()).toEqual('Invalid username and password.');
  });

  //Проверка изменения личных данных пользователем
});
