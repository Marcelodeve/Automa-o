package tests;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.*;
import utils.Browser;
import utils.Utils;

import static org.junit.Assert.*;

public class SetupTest extends BaseTests{

    @Test
    public void testOpeningBrowserAndLoadingPage(){
        assertTrue(Browser.getCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()));
        System.out.println("Abrimos o navegador e carregamos a url!");
    }

    @Test
    public void testlogin(){
        // Iniciar as páginas
        HomePage home = new HomePage();
        LoginPage login = new LoginPage();
        MyAccountPage myAccount = new MyAccountPage();

        home.clickBtnLogin();
        System.out.println("Clicou em Sign In e direcionou para a página de login");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=authentication&back=my-account")));

        login.fillEmail();
        System.out.println("Preencheu o email");
        login.fillPasswd();
        System.out.println("Preencheu a senha");
        login.clickBtnSubmitLogin();
        System.out.println("Clicou em Sign In");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=my-account")));
        System.out.println("Validou a URL de minha conta");
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT")); //My account maiúsculo,pois e case sensive
        System.out.println("Validou Minha Conta no site");

    }

    @Test
    public void testSearch() {

        String quest = "DRESS";
        String questResultQtd = "7";

        //Iniciar as páginas
        HomePage home = new HomePage();
        SearchPage search = new SearchPage();

        //Fazer a pesquisa
        home.doSearch(quest);

        //Validar a pesquisa
        assertTrue(search.isSearchPage());
        assertEquals(search.getTextLighter().replace("\"", ""), quest);
        assertThat(search.getTextHeading_counter(), CoreMatchers.containsString(questResultQtd));
    }

    @Test
    public void testAcessCategoryTShirts(){
        //Iniciar as páginas
        HomePage home = new HomePage();
        CategoryPage category = new CategoryPage();

        //Clicar na categoria T-SHIRTS
        home.clickCategoryTShirts();
        //Validar se ao clicar na categoria T-SHIRTS ocorre o direcionamento correto
        assertTrue(category.isPageTshirts());

    }

    @Test
    public void testAddProductToProductPage(){
        //Acessar a categoria T-shirts
        testAcessCategoryTShirts();

        //Iniciar as páginas
        CategoryPage category = new CategoryPage();
        ProductPage pdp = new ProductPage();

        //Salva nome do produto na categoria
        String nameProductCategory = category.getProductNameCategory();

        //Clicar em more e direcionar para a página do produto
        category.clickProductAddToProductPage();

        //Verificar se o produto está na página de detalhes do produto corretamente
        assertTrue(pdp.getProductNamePDP().equals(nameProductCategory));

    }

    @Test
    public void testAddProductToCartPage(){
        //Acessa a página de produto
        testAddProductToProductPage();
        //Iniciar as páginas
        ProductPage pdp = new ProductPage();
        CartPage cart = new CartPage();


        //Salvar o nome do produto na página de PDP
        String nameProductPDP = pdp.getProductNamePDP();

        //Clicar no botão de Adicionar ao carrinho
        pdp.clickButtonAddToCart();

        //Clicar no botão Proceed To Checkout da modal
        pdp.clickButtonModalProceedToCheckout();

        //Validação do nome do produto no carrinho
        assertTrue(cart.getNameProductCart().equals(nameProductPDP));

    }
}

