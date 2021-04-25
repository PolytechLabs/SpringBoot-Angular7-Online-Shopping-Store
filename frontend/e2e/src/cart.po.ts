import {element, by, protractor, ElementArrayFinder, browser} from 'protractor';
import { promise as wdpromise } from 'selenium-webdriver';

export class CartPageObject {

  private goToLoginLink;

  constructor() {
    this.goToLoginLink = element(by.id('checkout'));

  }

  navigateToLoginPage(){
    return this.goToLoginLink.click();
  }

  submitStuff(): wdpromise.Promise<void> {
    let submitButton = element(by.id('checkout'));
    return submitButton.sendKeys(protractor.Key.ENTER);
  }

  submitStuffs() {
    let submitButton = element(by.buttonText('Checkout'));
    return submitButton.click();
  }
}
