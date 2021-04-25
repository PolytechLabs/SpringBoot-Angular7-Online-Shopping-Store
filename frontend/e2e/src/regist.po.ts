import { element, by, protractor, ElementArrayFinder } from 'protractor';
import { promise as wdpromise } from 'selenium-webdriver';

export class RegistPage {
  private form;
  private title;
  private nameInput;
  private emailInput;
  private passwordInput;
  private phoneInput;
  private addressInput;
  private submitButton;


  constructor() {
    // get the relevant elements
    this.title = element(by.id('register-title'));
    this.nameInput = element(by.id('name'));
    this.emailInput = element(by.id('email'));
    this.passwordInput = element(by.id('password'));
    this.phoneInput = element(by.id('phone'));
    this.addressInput = element(by.id('address'));
    this.submitButton = element(by.id('submit'));

  }

  setName(value: string): wdpromise.Promise<void> {
    return this.nameInput.clear().sendKeys(value);
  }

  setEmail(value: string): wdpromise.Promise<void> {
    return this.emailInput.clear().sendKeys(value);
  }

  setPassword(value: string): wdpromise.Promise<void> {
    return this.passwordInput.clear().sendKeys(value);
  }

  setPhoneInput(value: string): wdpromise.Promise<void> {
    return this.phoneInput.clear().sendKeys(value);
  }

  setAddressInput(value: string): wdpromise.Promise<void> {
    return this.addressInput.clear().sendKeys(value);
  }

  getTitle(): wdpromise.Promise<string> {
    return this.title.getText();
  }

  submitForm(): wdpromise.Promise<void> {
    return this.submitButton.click();
  }

  formIsValid(): wdpromise.Promise<boolean> {
    return this.getAllErrorMessages().count().then(value => {
      return value === 0;
    });
  }

  private getAllErrorMessages(): ElementArrayFinder {
    return element.all(by.css('.error-group'));
  }

}
