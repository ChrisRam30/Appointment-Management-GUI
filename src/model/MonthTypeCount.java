package model;

public class MonthTypeCount {
    String monthName;
    String typeName;
    int count;

    public MonthTypeCount(String monthName, String typeName, int count) {
        this.monthName = monthName;
        this.typeName = typeName;
        this.count = count;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
