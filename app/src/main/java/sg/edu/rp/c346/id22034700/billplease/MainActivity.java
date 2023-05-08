package sg.edu.rp.c346.id22034700.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    EditText discount;
    TextView display;
    TextView displayEach;
    ToggleButton SVS;
    ToggleButton GST;
    RadioGroup rgPay;
    Button split;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextAmount);
        pax = findViewById(R.id.editTextPax);
        discount = findViewById(R.id.editTextDiscount);
        display = findViewById(R.id.display);
        displayEach = findViewById(R.id.displayEach);
        SVS = findViewById(R.id.toggleButtonSVS);
        GST = findViewById(R.id.toggleButtonGST);
        rgPay = findViewById(R.id.radioGroupPayment);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String show = "Total Bill: $";
                String show2 = "Each Pays: $";
                double doubleAmt = Double.valueOf(amount.getText().toString());
                int intPax = Integer.valueOf(pax.getText().toString());
                double doubleDiscount = Double.valueOf(discount.getText().toString());

                if(SVS.isChecked()){
                    double SVSDecimal = 1.10;
                }

                if(GST.isChecked()){
                    double GSTDecimal = 1.07;
                }

                if((SVS.isChecked())&&(GST.isChecked())){
                    double bothDecimal = 1.17;
                }

                int checkedRadioId = rgPay.getCheckedRadioButtonId();
                double finalAmt = ((doubleAmt * ((100 - doubleDiscount)/100)));
                if((SVS.isChecked())&&(GST.isChecked())){
                    finalAmt *= 1.17;
                } else if (SVS.isChecked()) {
                    finalAmt *= 1.10;
                } else if (GST.isChecked()) {
                    finalAmt *= 1.07;
                }
                String output = String.format("%.2f", finalAmt);
                show = show + output;
                display.setText(show);

                    if (checkedRadioId == R.id.radioButtonCash){
                        String show3 = " in cash.";
                        double eachAmtC = (((doubleAmt * ((100 - doubleDiscount)/100)))/intPax);
                        if((SVS.isChecked())&&(GST.isChecked())){
                            eachAmtC *= 1.17;
                        } else if (SVS.isChecked()) {
                            eachAmtC *= 1.10;
                        } else if (GST.isChecked()) {
                            eachAmtC *= 1.07;
                        }
                        String output2 = String.format("%.2f", eachAmtC);
                        show2 = show2 + output2 + show3;
                        displayEach.setText(show2);
                    } else {
                        String show3 = " via PayNow to 91239876.";
                        double eachAmtPN = (((doubleAmt * ((100 - doubleDiscount)/100)))/intPax);
                        if((SVS.isChecked())&&(GST.isChecked())){
                            eachAmtPN *= 1.17;
                        } else if (SVS.isChecked()) {
                            eachAmtPN *= 1.10;
                        } else if (GST.isChecked()) {
                            eachAmtPN *= 1.07;
                        }
                        String output2 = String.format("%.2f", eachAmtPN);
                        show2 = show2 + output2 + show3;
                        displayEach.setText(show2);
                    }
                }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.getText().clear();
                pax.getText().clear();
                discount.getText().clear();
            }
        });

    }
}