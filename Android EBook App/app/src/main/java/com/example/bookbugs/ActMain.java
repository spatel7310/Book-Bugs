package com.example.bookbugs;
/*
====================================================================

 Application: Book Bugs
 Activity:    Homework 1
 Course:      CSC 4330
 Homework:    1
 Author:      Sheev Patel
 Date:        2/1/2021
 Description:
   Android app to check out e-books.

====================================================================
*/

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActMain extends AppCompatActivity {

//Declaring variables
    public EditText name, email, bookPrice, tax, total, bookTitle;
    public String paymentType, title;
    public double price,salesTax;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);

        //"Connecting" the controls from layout to variables in main.
        name = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        bookTitle = findViewById(R.id.selectedBook);
        bookPrice = findViewById(R.id.bookCost);
        tax = findViewById(R.id.salesTax);
        total = findViewById(R.id.totalCost);


    }

    //Declaring the list of books as strings
    final String[] options = {"The Little Prince, $5.99", "The Lion, $12.29", "If Only, $1.79", "Twilight, $4.79", "The End, $2.99", "Book: 6, $4.99", "The Life, $2.38", "My Story, $1.99" };

    //function to show the Dialog list full of the books
    public void showListDialog(View v){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(v.getContext());
        builder.setTitle("Books");
        //inputting the different options and giving them each a number
        builder.setItems(options,
                new DialogInterface.OnClickListener()
                {
                    //Once an option is clicked, it will assign that value a number
                    public void onClick(DialogInterface dialog,
                                        int option)
                    {
                        //Assigns the option a title and a price
                        if (option == 0){
                            title = "The Little Prince";
                            price = 5.99;
                        }
                        else if (option == 1) {
                            title = "The Lion";
                            price = 12.29;
                        }
                        else if (option == 2){
                            title = "If Only";
                            price = 1.79;
                        }
                        else if (option == 3){
                            title = "Twilight";
                            price = 4.79;
                        }
                        else if (option == 4){
                            title = "The end";
                            price = 2.99;
                        }
                        else if (option == 5){
                            title = "Book: 6";
                            price = 4.99;
                        }
                        else if (option == 6){
                            title = "The Life";
                            price =2.38;
                        }
                        else {
                            title = "My Story";
                            price = 1.99;
                        }

                        //Calculating all the needed doubles and casting them to strings(for output).
                        salesTax = price*0.06;
                        String salesTaxString = String.valueOf(salesTax);
                        String orderCostString = String.valueOf(salesTax+price);
                        String priceString = String.valueOf(price);

                        //Setting the text values (strings) for the Edit Text controls.
                        bookTitle.setText(title);
                        bookPrice.setText(priceString);
                        tax.setText(salesTaxString);
                        total.setText(orderCostString);

                        //Flashing the option you selected.
                        Toast.makeText(getApplicationContext(),
                                "List dialog box: \"" + options [option] +
                                        "\" selected.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    //The following functions assign a value to the string "payment type" depending on what radio button is pressed.
    public void setMaster(View v){
        paymentType = "MasterCard";
    }

    public void setVisa(View v){
        paymentType = "VISA";
    }

    public void setPaypal(View v){
        paymentType = "PAYPAL";
    }

    //function that completely kills the app and has the user re-open it.
    public static void triggerRebirth(Context context, Class myClass) {
        Intent intent = new Intent(context, myClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Runtime.getRuntime().exit(0);
    }

    //function that is called when "SUBMIT" is pressed.
    public void showReceipt(View v){
        String s, e;

        //changing the name and email to string types from editText types.
        s = name.getText().toString();
        e = email.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Receipt");
        //output
        builder.setMessage("\nBook Purchased: " + title + "\nName: " + s + "\nEmail: " + e + "\nBook Price: " + price + "\nSales Tax: " + salesTax + "\nTotal Cost: " + (salesTax+price) + "\nPayment Type: " + paymentType);
        builder.setPositiveButton("OK", null);
        builder.show();

    }
}