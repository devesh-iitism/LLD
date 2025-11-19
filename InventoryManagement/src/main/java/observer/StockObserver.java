package observer;

import entities.StockItem;

public interface StockObserver {
    void onStockUpdate(StockItem stockItem);
}