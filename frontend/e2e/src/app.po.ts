import { browser, by, element } from 'protractor';

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

  getTitleText() {
    return element(by.css('app-root h1')).getText();
  }

  goToRegisterPage() {
    return browser.get(`${this.pages['register']}`);
  }
}
