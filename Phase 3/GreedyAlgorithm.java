public class GreedyAlgorithm {

    // Create parcel objects
    Parcel parcelA = new Parcel('A');
    Parcel parcelB = new Parcel('B');
    Parcel parcelC = new Parcel('C');

    public final int WIDTH = 5;
    public final int LENGTH = 33;
    public final int HEIGHT = 8;
    public int[][][] truck = new int[WIDTH][LENGTH][HEIGHT];
    public int score = 0;

    public void fillTruck() {
        if (placementAttempt(parcelC)) {
            while (placementAttempt(parcelC)) {
                // Do nothing, just continue placing parcelC
                printMatrix(); // Optional: Print the truck after each placement
            }
        }
    
        if (placementAttempt(parcelB)) {
            while (placementAttempt(parcelB)) {
                // Do nothing, just continue placing parcelB
                printMatrix(); // Optional: Print the truck after each placement
            }
        }
    
        if (placementAttempt(parcelA)) {
            while (placementAttempt(parcelA)) {
                // Do nothing, just continue placing parcelA
                printMatrix(); // Optional: Print the truck after each placement
            }
        }
    }

    public boolean placementAttempt(Parcel parcel) {
        int[][][] array = parcel.getParcelArray();
        for (int x = 0; x < truck.length - array.length + 1; x++) {
            for (int y = 0; y < truck[0].length - array[0].length; y++) {
                for (int z = 0; z < truck[0][0].length - array[0][0].length; z++) {
                    if (canAdd(parcel, x, y, z)) {
                        addParcel(parcel, x, y, z);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean canAdd(Parcel parcel, int x, int y, int z) {
        int[][][] array = parcel.getParcelArray();
        for (int i = x; i < x + array.length; i++) {
            for (int j = y; j < y + array[0].length; j++) {
                for (int k = z; k < z + array[0][0].length; k++) {
                    if (truck[i][j][k] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int addParcel(Parcel parcel, int x, int y, int z) {
        int[][][] array = parcel.getParcelArray();
        for (int i = x; i < x + array.length; i++) {
            for (int j = y; j < y + array[0].length; j++) {
                for (int k = z; k < z + array[0][0].length; k++) {
                    truck[i][j][k] = parcel.getNum();
                }
            }
        }
        score += parcel.getValue();
        return score;
    }

    private boolean isTruckFilled() {
        // Check if the entire truck is filled
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                for (int k = 0; k < HEIGHT; k++) {
                    if (truck[i][j][k] == 0) {
                        return false; // Not completely filled
                    }
                }
            }
        }
        return true; // Completely filled
    }

    public void printMatrix() {
        System.out.println("Final Truck Matrix:");
        for (int i = 0; i < truck.length; i++) {
            for (int j = 0; j < truck[0].length; j++) {
                for (int k = 0; k < truck[0][0].length; k++) {
                    System.out.print(truck[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(score);
    }

    public static void main(String[] args) {
        GreedyAlgorithm alg = new GreedyAlgorithm();
        alg.fillTruck();
        alg.printMatrix();
    }
}
