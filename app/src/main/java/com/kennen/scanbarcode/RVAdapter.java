package com.kennen.scanbarcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.kennen.scanbarcode.MainActivity.myDB;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>
{
    private Context context;
    private List<Product> data;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView code_infor, price_infor;
        private Button delete_infor;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            code_infor = (TextView)itemView.findViewById(R.id.tv_codeInformation);
            price_infor = (TextView)itemView.findViewById(R.id.tv_priceInformation);
            delete_infor = (Button)itemView.findViewById(R.id.btn_delete);
        }
    }

    public RVAdapter(Context context, List<Product> data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.product_infor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position)
    {
        Product temp = data.get(position);
        holder.code_infor.setText(temp.getBarCode());
        holder.price_infor.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(Integer.valueOf(temp.getPrice())));
        holder.delete_infor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                myDB.Delete(holder.code_infor.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }
}
