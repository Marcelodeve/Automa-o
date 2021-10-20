package elementMapper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPageElementMapper {

    @FindBy(className = "cat-name")
    public WebElement nameCategoryTShirts;

    @FindBy(css = ".product-countainer")
    public WebElement product;

    @FindBy(css = ".button[title=View]")
    public WebElement buttonMoreAddToProductPage;

    @FindBy(css = ".product_list .product-name")
    public WebElement productNameCategory;


}
