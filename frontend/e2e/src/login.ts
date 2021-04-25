import {element, by, protractor, ElementArrayFinder, browser} from 'protractor';
import { promise as wdpromise } from 'selenium-webdriver';

export class LoginPageObject {

  private form;
  private passwordInput;
  private managerInput;
  private emailInput;
  private submitButton;
  private goToRegisterLink;
  private title;

  constructor() {
    this.passwordInput = element(by.id('password'));
    this.managerInput = element(by.id('manager'));
    this.emailInput = element(by.id('email'));
    this.submitButton = element(by.id('login-submit'));
    this.goToRegisterLink = element(by.id('login-register-link'));
  }

  setEmail(value: string): wdpromise.Promise<void> {
    return this.emailInput.clear().sendKeys(value);
  }

  setPassword(value: string): wdpromise.Promise<void> {
    return this.passwordInput.clear().sendKeys(value);
  }

  setManager(): wdpromise.Promise<void> {
    return this.managerInput.sendKeys(protractor.Key.ENTER);
  }

  submitForm(){
    let submitButton = element(by.id('login-submit'));
    return submitButton.click();
  }

  navigateToRegisterPage(): wdpromise.Promise<void> {
    return this.goToRegisterLink.click();
  }

  goToMainPage(): wdpromise.Promise<void> {
    return browser.get('/product');
  }

  getTitleText() {
    return element(by.id('invalid')).getText();
  }
}
