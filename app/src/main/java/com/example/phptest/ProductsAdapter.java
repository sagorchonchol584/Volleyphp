package com.example.phptest;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


import java.util.List;
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private Context mContext;
    private List<Product> ProductList;
    protected ItemListener mListener;


    public ProductsAdapter(Context mContext, List<Product> productList, ItemListener itemListener) {
        this.mContext = mContext;
        this.ProductList = productList;
        mListener=itemListener;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview, null);
        return new ProductViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = ProductList.get(position);


        Glide.with(mContext).load(product.getProductimage()).into(holder.imageView);
        holder.textViewTitle.setText(product.getProducttitle());


      //  holder.textViewShortDesc.setText(product.getProductdescription());
      //  holder.textViewRating.setText("Rating :"+String.valueOf(product.getProductrating()));
       // holder.textCategory.setText(String.valueOf(product.getProductcategory()));
      //  holder.textViewPrice.setText("Rs. "+String.valueOf(product.getProductprice()));
         holder.id=product.getProductid();


    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewShortDesc, textViewRating, textCategory, textViewPrice;
        ImageView imageView;
        public int id;
        public String add;
        public ProductViewHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(this);

          textViewTitle = itemView.findViewById(R.id.textViewTitle);
       //    textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
        //    textViewRating = itemView.findViewById(R.id.textViewRating);
         //   textCategory=itemView.findViewById(R.id.textCategory);
         //  textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }




        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(id);

                Log.e("testttttttttttttttttt","click"+id);

            }

        }
    }

    public interface ItemListener {
        void onItemClick(int item);
    }
}
