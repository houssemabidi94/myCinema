import { browser, by, element } from 'protractor';
import { Alert } from 'selenium-webdriver';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  getTitleText() {
    return element(by.id('title')).getText() as Promise<string>;
  }
  selectVille(){
    let ville = element.all(by.css(".list-group li")).first();
    ville.click();
    return ville.getText() as Promise<string>;
  }
  selectCinema(){
   let cinema = element.all(by.css(".cinemasMargsBottom li a")).first();
   cinema.click();
   return cinema.getText();
  }
  selectProj(){
    let proj = element.all(by.id("proj")).first();
    proj.click();
    element.all(by.id("ticket")).first().click();
    element(by.name("nomClient")).sendKeys("Houssem Abidi");
    element(by.name("codePayment")).sendKeys("123456");
    element(by.id("payer")).click();
  }
}
