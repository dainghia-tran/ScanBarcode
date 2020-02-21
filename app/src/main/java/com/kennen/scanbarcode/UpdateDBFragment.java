package com.kennen.scanbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.kennen.scanbarcode.MainActivity.myDB;

public class UpdateDBFragment extends Fragment
{
    private IntentIntegrator tempIntentInt;
    private View rootView;
    private Button updateDB, scanToInput;
    private EditText barCode, price;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.update_frag, container, false);
        updateDB = rootView.findViewById(R.id.btn_update);
        scanToInput = rootView.findViewById(R.id.btn_scanToInput);
        barCode = rootView.findViewById(R.id.et_code);
        price = rootView.findViewById(R.id.et_price);
        tempIntentInt = IntentIntegrator.forSupportFragment(UpdateDBFragment.this);

        scanToInput.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tempIntentInt.setPrompt("Đưa mã vào vạch đỏ");
                tempIntentInt.initiateScan();
            }
        });

        updateDB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(barCode.getText().toString().isEmpty() || price.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Vui lòng nhập vào đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else
                {
                    if(myDB.getProduct(barCode.getText().toString()) == null)
                    {
                        myDB.Insert(new Product(barCode.getText().toString(), price.getText().toString()));
                        Toast.makeText(getContext(), "Đã thêm vào sản phẩm " + barCode.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        myDB.Update(new Product(barCode.getText().toString(), price.getText().toString()));
                        Toast.makeText(getContext(), "Đã cập nhật giá sản phẩm " + barCode.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            if (result.getContents() == null)
            {
                Toast.makeText(getContext(), "Đã hủy quét mã", Toast.LENGTH_LONG).show();
            } else
            {
                barCode.setText(result.getContents());
            }
        } else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
