/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FavouriteLinkTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void favouriteLinkGonzalo() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "28985178A: Martínez Fernández, Gonzalo");
		super.checkCurrentUrl(
				"https://www.youtube.com/watch?v=idn2UdOu5bE&list=PLkYgGmsQ2duxgqf7SI43cStQVXHCbRF9a&index=7");
	}

	@Test
	@Order(20)
	public void favouriteLinkJaimeM() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "30270882F: Moscoso Bernal, Jaime");
		super.checkCurrentUrl("https://www.twitch.tv/alexelcapo");
	}

	@Test
	@Order(30)
	public void favouriteLinkAntonio() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "79147298C: Campos Gil, Antonio");
		super.checkCurrentUrl("https://consent.youtube.com/m?continue=https%3A%2F%2Fwww.youtube.com%2Fshorts%2FXec2NPpXsUI%3Fcbrd%3D1&gl=ES&m=0&pc=yt&uxe=eomty&hl=en-GB&src=1");
	}

	@Test
	@Order(40)
	public void favouriteLinkAna() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "47258017D: Conde Marrón, Ana");
		super.checkCurrentUrl("https://www.zalando.es/mujer-home");
	}

	@Test
	@Order(50)
	public void favouriteLinkEnrique() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "49826048K: Muñoz Pérez, Enrique");
		super.checkCurrentUrl("https://www.realbetisbalompie.es");
	}

	@Test
	@Order(60)
	public void favouriteLinkJaimeB() {
		super.navigateHome();
		super.clickOnMenu("Anonymous", "77926303A: Borrego Conde, Jaime");
		super.checkCurrentUrl("https://www.youtube.com/watch?v=vTOurabBRxY&ab_channel=LinkSpets");
	}

}
