package inventory;

import entities.Product;
import entities.StockItem;
import entities.Transaction;
import entities.Warehouse;
import enums.TransactionType;
import observer.LowStockAlertObserver;
import service.AuditService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryManager {
    private static final InventoryManager INSTANCE = new InventoryManager();
    private final Map<String, Product> products;
    private final Map<Integer, Warehouse> warehouses;
    private final AuditService auditService;

    private InventoryManager() {
        this.products = new ConcurrentHashMap<>();
        this.warehouses = new ConcurrentHashMap<>();
        this.auditService = AuditService.getInstance();
    }

    public static InventoryManager getInstance() {
        return INSTANCE;
    }

    public Warehouse addWarehouse(int warehouseId, String location) {
        Warehouse warehouse = new Warehouse(warehouseId, location);
        warehouses.put(warehouseId, warehouse);
        return warehouse;
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void addProductToWarehouse(String productId, int warehouseId, int initialQuantity, int threshold) {
        Warehouse warehouse = warehouses.get(warehouseId);
        Product product = products.get(productId);

        if (warehouse == null || product == null) {
            System.err.println("Warehouse or product not found");
        }

        StockItem stockItem = new StockItem(product, initialQuantity, threshold, warehouseId);
        stockItem.addObserver(new LowStockAlertObserver()); // Register the observer
        warehouse.addProductStock(stockItem);

        // Log the initial stock
        auditService.log(new Transaction(product.getProductId(), warehouseId, initialQuantity, TransactionType.INITIAL_STOCK));
    }

    private void updateStock(int warehouseId, String productId, int quantityChange) {
        Warehouse warehouse = warehouses.get(warehouseId);

        if (warehouse == null) {
            System.err.println("Error: Warehouse " + warehouseId + " not found.");
            return;
        }

        boolean success = warehouse.updateStock(productId, quantityChange);

        if (success) {
            auditService.log(new Transaction(productId, warehouseId, quantityChange,
                    quantityChange >= 0 ? TransactionType.ADD : TransactionType.REMOVE));
        }
    }

    public void addStock(int warehouseId, String productId, int quantity) {
        updateStock(warehouseId, productId, quantity);
    }

    public void removeStock(int warehouseId, String productId, int quantity) {
        updateStock(warehouseId, productId, -quantity);
    }

    public void viewInventory(int warehouseId) {
        Warehouse warehouse = warehouses.get(warehouseId);
        if (warehouse != null) {
            warehouse.printInventory();
        } else {
            System.err.println("Warehouse with ID " + warehouseId + " not found.");
        }
    }

    public void viewAuditLog() {
        auditService.printAuditLog();
    }
}
