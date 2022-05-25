import { AppPage } from './app.po';
import { browser, logging, protractor } from 'protractor';

var origFn = browser.driver.controlFlow().execute;
browser.driver.controlFlow().execute = function() {
  var args = arguments;

  // queue 100ms wait
  origFn.call(browser.driver.controlFlow(), function() {
    return protractor.promise.delayed(100);
  });

  return origFn.apply(browser.driver.controlFlow(), args);
};
describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Gestion Cinémas');
  });
  it('should select first ville', () => {
    expect(page.selectVille()).toEqual('Casablanca');
  });
  it('should select first cinema', () => {
    expect(page.selectCinema()).toEqual('MegaRAMA');
  });
  it('should select first projection and get tickets', () => {
    page.selectProj();
    expect(page.getAlert()).toEqual("Tickets réservés avec succès!");
  });
  
  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
