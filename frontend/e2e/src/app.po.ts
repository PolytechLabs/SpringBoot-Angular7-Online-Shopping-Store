import {browser, by, element, protractor} from 'protractor';
import {promise as wdpromise} from "selenium-webdriver";

export class AppPage {

  private pages: Object;

  constructor() {

    this.pages = {
      'login': 'login',
      'forgotPassword': 'forgot-password',
      'register': 'register',
      'setNewPassword': 'set-new-password'
    };

  }

  navigateTo() {
    return browser.get('/');
  }

  goToProduct() {
    return browser.get('product/B0002');
  }

  goToCart() {
    return browser.get('cart');
  }

  submitForm(): wdpromise.Promise<void> {
    let submitButton = element(by.id('addToCart'));
    return submitButton.sendKeys(protractor.Key.ENTER);
  }

  getTitleText() {
    return element(by.css('app-root h1')).getText();
  }

  goToRegisterPage() {
    return browser.get(`${this.pages['register']}`);
  }

  submitStuff(): wdpromise.Promise<void> {
    let submitButton = element(by.id('checkout'));
    return submitButton.sendKeys(protractor.Key.ENTER);
  }
}
