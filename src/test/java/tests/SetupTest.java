package tests;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pageObjects.*;
import utils.Browser;
import utils.Utils;

import static org.junit.Assert.*;

public class SetupTest extends BaseTests {

    @Test
    public void testOpeningBrowserAndLoadingPage() {
        assertTrue(Browser.getCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()));
        System.out.println("Abrimos o navegador e carregamos a url!");
    }

    @Test
    public void testlogin() {
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
    public void testAcessCategoryTShirts() {
        //Iniciar as páginas
        HomePage home = new HomePage();
        CategoryPage category = new CategoryPage();

        //Clicar na categoria T-SHIRTS
        home.clickCategoryTShirts();
        //Validar se ao clicar na categoria T-SHIRTS ocorre o direcionamento correto
        assertTrue(category.isPageTshirts());

    }

    @Test
    public void testAddProductToProductPage() {
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
    public void testAddProductToCartPage() {
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

    @Test // DESAFIO 1 - FLUXO DE CRIAR CONTA
    public void createAccount() {

        // Iniciar as páginas
        HomePage home = new HomePage();
        LoginPage login = new LoginPage();
        MyAccountPage myAccount = new MyAccountPage();

        // Clicou em Sign In
        home.clickBtnLogin();
        System.out.println("Clicou em Sign In e direcionou para a página de login");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=authentication&back=my-account")));

        //Preencheu o email
        Browser.getCurrentDriver().findElement(By.id("email_create")).
                sendKeys("ayesetmarcelo@gmail.com");
        System.out.println("Preencheu o email para cadastrar");

        //Clicou em criar conta
        Browser.getCurrentDriver().findElement(By.id("SubmitCreate")).click();
        System.out.println("Clicou em Create an account");

        //Preencheu as informações pessoais
        Browser.getCurrentDriver().findElement(By.id("id_gender1")).click();
        Browser.getCurrentDriver().findElement(By.id("customer_firstname")).sendKeys("Walter");
        Browser.getCurrentDriver().findElement(By.id("customer_lastname")).sendKeys("Sachet");
        Browser.getCurrentDriver().findElement(By.id("passwd")).sendKeys("12345");
        System.out.println("Você preencheu as informações pessoais");

        //Preencheu as informações de endereço
        Browser.getCurrentDriver().findElement(By.id("firstname")).sendKeys("");
        Browser.getCurrentDriver().findElement(By.id("lastname")).sendKeys("");
        Browser.getCurrentDriver().findElement(By.id("address1")).sendKeys("Rua Garfield,3080");
        Browser.getCurrentDriver().findElement(By.id("city")).sendKeys("Arroio dos Ratos");
        Browser.getCurrentDriver().findElement(By.id("postcode")).sendKeys("00000");
        Browser.getCurrentDriver().findElement(By.id("id_state")).click();
        Browser.getCurrentDriver().findElement(By.id("id_state")).sendKeys("iowa");
        Browser.getCurrentDriver().findElement(By.id("phone_mobile")).sendKeys("99999999");
        Browser.getCurrentDriver().findElement(By.id("id_country")).click();
        Browser.getCurrentDriver().findElement(By.id("id_country")).sendKeys("united states");
        System.out.println("Você preencheu as informações de endereço");

        //Registrou a conta
        Browser.getCurrentDriver().findElement(By.id("submitAccount")).click();
        System.out.println("Você registrou a conta");

        //Validar Conta
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=my-account")));
        System.out.println("Validou a URL de minha conta");
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("Validou Minha Conta no site");

        // Deslogar da conta
        Browser.getCurrentDriver().findElement(By.className("logout")).click();
        System.out.println("Você deslogou da conta");

    }

    @Test // DESAFIO 2 - FLUXO DE COMPRA
    public void buyTest() {
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

        Browser.getCurrentDriver().findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();
        Browser.getCurrentDriver().findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button")).click();
        Browser.getCurrentDriver().findElement(By.id("cgv")).click();
        Browser.getCurrentDriver().findElement(By.xpath("//*[@id=\"form\"]/p/button")).click();
        assertTrue(Browser.getCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()
                .concat("index.php?controller=order&multi-shipping=")));
        assertTrue(Browser.getCurrentDriver().findElement(By.xpath("//*[@id=\"product_1_1_0_588217\"]/td[2]/p/a"))
                .getText().contains("Faded Short Sleeve T-shirts"));
        Browser.getCurrentDriver().findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        Browser.getCurrentDriver().findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
        assertTrue(Browser.getCurrentDriver().findElement(By.id("order-confirmation"))
                .getText().contains("ORDER CONFIRMATION"));
        System.out.println("Você realizou a compra com sucesso!");

        // Deslogar da conta
        Browser.getCurrentDriver().findElement(By.className("logout")).click();
        System.out.println("Você deslogou da conta");

    }

}