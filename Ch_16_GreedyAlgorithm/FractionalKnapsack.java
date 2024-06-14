class Item {
    double weight;
    double profit;
    double ratio;
    public Item(double weight, double profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = profit / weight;
    }
}
public class FractionalKnapsack {
    public static double getMaxProfit(Item[] items, double capacity) {
        bubbleSort(items);
        double maxProfit = 0.0;
        double currentWeight = 0.0;
        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                maxProfit += item.profit;
            } 
            else {
                double remainingCapacity = capacity - currentWeight;
                maxProfit += (item.ratio) * remainingCapacity;
                break;
            }
        }
        return maxProfit;
    }
    public static void bubbleSort(Item[] items) {
        int n = items.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (items[j].ratio < items[j + 1].ratio) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(3, 300),
                new Item(2, 190),
                new Item(2, 180)
        };
        double capacity = 4;
        double maxProfit = getMaxProfit(items, capacity);
        System.out.println("Maximum profit: " + maxProfit);
    }
}
