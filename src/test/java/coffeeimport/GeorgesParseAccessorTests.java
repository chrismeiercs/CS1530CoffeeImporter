package coffeeimport;

import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.DeleteCallback;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.SaveCallback;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.*;
import java.io.*;
import java.util.Date;

/**
 * Created by George 7/1/2015
 */
public class GeorgesParseAccessorTests
{
    public static void main(String [] args)
    {
        ParseAccessor parseAccessor;
        Product testProduct;
        parseAccessor = new ParseAccessor("lXpYJrImjyl3YSKDvxX9R6H3GGqIKQrB6WbI6Eu1", "tmfM1mnCsFEE9onzPSAqwKubvgKakqPppNx8dnsq");

        testProduct = new Product();
        testProduct.setProductId("AAA12345");
        testProduct.setProductName("George's Coffee");
        testProduct.setProductCost(100);
        testProduct.setHasBeenSold(false);
        testProduct.setPriceSold(150);


        parseAccessor.updateProduct(testProduct);
    }

}
