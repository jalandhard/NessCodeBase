package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import pageObjects.CheckoutPage;

public class CheckoutPageSteps {
	TestContext testContext;
	CheckoutPage checkoutPage;
	
	public CheckoutPageSteps(TestContext context) {
		testContext = context;
		checkoutPage = testContext.getPageObjectManager().getCheckoutPage();
	}
	
	@And("^enter personal details on checkout page$")
	public void enter_personal_details_on_checkout_page(){
		checkoutPage.fill_PersonalDetails();	
	}
	
	@And("^select same delivery address$")
	public void select_same_delivery_address(){
		checkoutPage.check_ShipToDifferentAddress(false);
	}
	
	@And("^select payment method as \"([^\"]*)\" payment$")
	public void select_payment_method_as_payment(String arg1){
		checkoutPage.select_PaymentMethod("CheckPayment");
	}

	@And("^place the order$")
	public void place_the_order() {
		checkoutPage.check_TermsAndCondition(true);
		checkoutPage.clickOn_PlaceOrder();		
		testContext.getWebDriverManager().closeDriver();
	}	

}