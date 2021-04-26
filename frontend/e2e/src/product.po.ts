import {element, by, protractor, ElementArrayFinder, browser} from 'protractor';
import { promise as wdpromise } from 'selenium-webdriver';

export class ProductPageObject {

  private goToEditPage;
  private goToOrders;
  private cancelOrder;
  private password;
  private nameN;
  private named;
  private description;
  private status;

  constructor() {
    this.goToEditPage = element(by.id('editPr'));
    this.goToOrders = element(by.id('orders'));
    this.status = element(by.id('status'));
    this.password = element(by.id('passworded'));
    this.cancelOrder = element(by.id('cancel'));
    this.nameN = element(by.id('nameN'));
    this.named = element(by.id('named'));
    this.description = element(by.id('productDescription'));
  }

  navigateToEditPage(){
    return this.goToEditPage.sendKeys(protractor.Key.ENTER);
  }

  navigateToOrders(){
    return this.goToOrders.sendKeys(protractor.Key.ENTER);
  }

  navigateToData(){
    return this.nameN.sendKeys(protractor.Key.ENTER);
  }

  setNewDescription(value: string): wdpromise.Promise<void> {
    return this.description.clear().sendKeys(value);
  }

  setPassword(value: string): wdpromise.Promise<void> {
    return this.password.clear().sendKeys(value);
  }

  submitName(){
    let submitButton = element(by.id('submited'));
    return submitButton.click();
  }

  setNewName(value: string): wdpromise.Promise<void> {
    return this.named.clear().sendKeys(value);
  }

  submitForm(){
    let submitButton = element(by.id('submit-edit'));
    return submitButton.click();
  }

  doCancelOrder(): wdpromise.Promise<void> {
    return this.cancelOrder.sendKeys(protractor.Key.ENTER);
  }

  getTitleText() {
    return element(by.id('products')).getText();
  }

  getStatus() {
    return this.status.getText();
  }

  getDescription() {
    return this.description.getAttribute('value');
  }

  getName() {
    return this.named.getAttribute('value');
  }


}
