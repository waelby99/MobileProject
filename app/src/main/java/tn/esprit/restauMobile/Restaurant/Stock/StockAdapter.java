package tn.esprit.restauMobile.Restaurant.Stock;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.daos.StockDAO;
import tn.esprit.restauMobile.entities.Stock;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> {

    private List<Stock> stockList;
    private StockDAO stockDAO; // Add StockDAO here

    public StockAdapter(List<Stock> stockList, StockDAO stockDAO) {
        this.stockList = stockList;
        this.stockDAO = stockDAO; // Initialize StockDAO
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock, parent, false);
        return new StockViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.productNameTextView.setText(stock.getProductName());
        holder.quantityTextView.setText("Quantity: " + stock.getQuantity());

        // Set up buttons for edit and delete here
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditStockActivity.class);
            intent.putExtra("stockId", stock.getStockId());
            v.getContext().startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            // Implement delete stock functionality
            stockDAO.deleteStock(stock);  // Example, you may need to handle the deletion properly
            stockList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameTextView, quantityTextView;
        public Button editButton, deleteButton; // Add buttons for edit and delete

        public StockViewHolder(View view) {
            super(view);
            productNameTextView = view.findViewById(R.id.productNameTextView);
            quantityTextView = view.findViewById(R.id.quantityTextView);
            editButton = view.findViewById(R.id.editButton);  // Add the edit button
            deleteButton = view.findViewById(R.id.deleteButton);  // Add the delete button
        }
    }
}
