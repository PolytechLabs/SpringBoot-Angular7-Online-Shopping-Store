import { element, by, protractor, ElementArrayFinder } from 'protractor';
import { promise as wdpromise } from 'selenium-webdriver';

export class LoginPageObject {

  private form;
  private passwordInput;
  private emailInput;
  private submitButton;
  private goToForgotPasswordLink;
  private goToRegisterLink;
  private title;

  constructor() {
    this.goToRegisterLink = element(by.id('login-register-link'));

  }

  navigateToRegisterPage(): wdpromise.Promise<void> {
    return this.goToRegisterLink.click();
  }
}
