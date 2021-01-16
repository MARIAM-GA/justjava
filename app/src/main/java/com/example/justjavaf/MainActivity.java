package com.example.justjavaf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
public class MainActivity extends AppCompatActivity {
    int quantity = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus button is clicked.
     */

    public void increment(android.view.View view) {
        if( quantity ==100) {
            return ;
        }
        quantity = quantity + 1;
        displayquantity(quantity);
/**
 * This method is called when the minus button is clicked.
 */
    }
    public void decrement(android.view.View view) {
        if( quantity ==1) {
            return ;
        }
        quantity = quantity - 1 ;
        displayquantity(quantity);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(android.view.View view) {
        EditText nameField =(EditText) findViewById(R.id.name_field);
        String name =   nameField.getText().toString();
        // Figure out if the user wants whipped cream topping
        CheckBox WhippedCreamCheckBox =(CheckBox) findViewById(R.id.Whipped_cream_checkbox);
        boolean hasWhippedCream =   WhippedCreamCheckBox.isChecked();
        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox =(CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate =   chocolateCheckBox.isChecked();
        displayquantity(quantity);
        // Calculate the price
        int price =calculatePrice(hasWhippedCream,hasChocolate);
        // Display the order summary on the screen
        String priceMessage =creatOrderSummary(name, price, hasWhippedCream,hasChocolate);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "JUST JAVA ORDER FOR"+name);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }


    /**
     * Calculates the price of the order.
     *@param addWhippedCream is whether or not the user wants Whipped Cream topping
     * @param addChocolate    is whether or not to add chocolate to the coffee
     *  @return total price
     */
    private int  calculatePrice(boolean addWhippedCream,boolean addChocolate) {
        int basePrice =5;
        if( addWhippedCream) {
            basePrice= basePrice+1;
        }
        if( addChocolate) {
            basePrice= basePrice+2;
        }
        return  quantity * basePrice;
    }
    /**
     * create Summary of the order
     *@param name of the customer
     *@param price of the order
     *@param addWhippedCream is whether or not the user wants Whipped Cream topping
     * @param addChocolate    is whether or not to add chocolate to the coffee
     *@return text summary
     */
    private String creatOrderSummary(String name, int price, boolean addWhippedCream,boolean addChocolate ){

        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage +=  "\nAdd whipped cream?"+addWhippedCream;
        priceMessage +=  "\nAdd Chocolate?"+addChocolate;
        priceMessage +=  "\nQuantity: "+ quantity;
        priceMessage +=  "\ntotal: $" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

}
    /*
This method displays the given text on the screen.
*/
