import {element, by, protractor, ElementArrayFinder, browser} from 'protractor';
import { promise as wdpromise } from 'selenium-webdriver';

export class ProductPageObject {

  private goToEditPage;
  private description;

  constructor() {
    this.goToEditPage = element(by.id('editPr'));
    this.description = element(by.id('productDescription'));
  }

  navigateToEditPage(){
    return this.goToEditPage.sendKeys(protractor.Key.ENTER);
  }

  setNewDescription(value: string): wdpromise.Promise<void> {
    return this.description.clear().sendKeys(value);
  }

  submitForm(){
    let submitButton = element(by.id('submit-edit'));
    return submitButton.click();
  }

  getTitleText() {
    return element(by.id('products')).getText();
  }
}
