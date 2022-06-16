package lesson5;

class AppData {
    private final String[] header;
    private final int[][] data;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String head: header) {
            str.append(head).append(";");
        }
        str.append("\n");
        for (int[] dates: data) {
            for (int d: dates) {
                str.append(d).append(";");
            }
            str.append("\n");
        }
        return String.valueOf(str);
    }
}
