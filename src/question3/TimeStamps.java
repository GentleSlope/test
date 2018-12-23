package question3;

class TimeStamps {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private long stamp;//时间戳
    private int flatYear[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};//平年各月天数
    private int leapYear[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};//闰年各月天数

    public TimeStamps(int year, int month, int day, int hour, int minute, int second) {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(year, month, day, flatYear, leapYear);
        this.setHour(hour);
        this.setMinute(minute);
        this.setSecond(second);

    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public long getStamp() {
        return stamp;
    }

    public int[] getFlatYear() {
        return flatYear;
    }

    public int[] getLeapYear() {
        return leapYear;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        }
    }

    public void setDay(int year, int month, int day, int flatYear[], int leapYear[]) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//根据前面已经输入的年份和月份，判断输入day的范围
            if (day >= 1 && day <= leapYear[month - 1]) {
                this.month = month;
            }
        } else {
            if (day >= 1 && day <= flatYear[month - 1]) {
                this.month = month;

            }
        }
    }

    public void setHour(int hour) {
        if (hour >= 1 && hour <= 23) {
            this.hour = hour;
        }
    }

    public void setMinute(int minute) {
        if (minute >= 0 && minute <= 59) {
            this.minute = minute;
        }
    }

    public void setSecond(int second) {
        if (second >= 0 && second <= 59) {
            this.second = second;
        }
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
    }

    public int timeStamp(int year, int month, int day, int hour, int minute, int second, int flatYear[], int leapYear[]) {//从时间转换到时间戳
        int lSecond = 364 * 24 * 60 * 60 + 16 * 60 * 60;//计算从1970-1-1 8:00:00到1970-12-31 24:00:00的秒数
        int rSecond = 0;                     //声明从输入年份的1-1 0:00:00到输入日期的秒数
        int allSecond = 0;
        int temp = 0;//记录天数
        int count = 0;//用来计数从1970到year的闰年个数
        if (year > 1970) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//计算右时间
                for (int i = 0; i < month - 1; i++) {
                    temp += leapYear[i];//右月数
                }
            } else {
                for (int i = 0; i < month - 1; i++) {
                    temp += flatYear[i];
                }
            }
            rSecond = (temp + day - 1) * 24 * 60 * 60 + hour * 60 * 60 + minute * 60 + second;
            for (int j = 1971; j < year; j++) {
                if (j % 4 == 0 && j % 100 != 0 || j % 400 == 0) {
                    count++;
                }
            }
            allSecond = ((year - 1971) * 365 + count) * 24 * 60 * 60 + lSecond + rSecond;
        } else if (year == 1970) {
            for (int i = 0; i < month - 1; i++) {
                temp += flatYear[i];
            }
            allSecond = (temp + day - 1) * 24 * 60 * 60 + (hour - 8) * 60 * 60 + minute * 60 + second;
        } else {
            System.out.println("年份<1970,错误");
        }
        return allSecond;


    }

    public String stampTime(long stamp, int flatYear[], int leapYear[]) {//从时间戳到时间
        int year = 1970;//时间戳为0时的时间
        int month = 1;
        int day = 1;
        int hour = 8;
        int minute = 0;
        int second = 0;
        year = getYear(stamp, flatYear, leapYear);
        month = getMonth(stamp, year, flatYear, leapYear);
        day = getDay(year, month, stamp, flatYear, leapYear);
        hour = getHour(year, month, day, stamp, flatYear, leapYear);
        minute = getMinute(year, month, day, hour, stamp, flatYear, leapYear);
        second = getSecond(year, month, day, hour, minute, stamp, flatYear, leapYear);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

    }

    public int getYear(long stamp, int flatYear[], int leapYear[]) {
        int year = 1970;
        for (int i = 0; i < 100; i++) {
            if (stamp <= timeStamp(1970 + i, 12, 31, 23, 59, 59, flatYear, leapYear)) {
                year = 1970 + i;
                break;
            }
        }
        return year;
    }

    public int getMonth(long stamp, int year, int flatYear[], int leapYear[]) {
        int month = 1;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            for (int i = 1; i <= 12; i++) {
                if (stamp <= timeStamp(year, i, leapYear[i - 1], 23, 59, 59, flatYear, leapYear)) {
                    month = i;
                    break;
                }

            }
        } else {
            for (int i = 1; i <= 12; i++) {
                if (stamp <= timeStamp(year, i, flatYear[i - 1], 23, 59, 59, flatYear, leapYear)) {
                    month = i;
                    break;
                }

            }
        }
        return month;
    }

    public int getDay(int year, int month, long stamp, int flatYear[], int leapYear[]) {
        int day = 1;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            for (int i = 1; i <= leapYear[month - 1]; i++) {
                if (stamp <= timeStamp(year, month, i, 23, 59, 59, flatYear, leapYear)) {
                    day = i;
                    break;
                }
            }
        } else {
            for (int i = 1; i <= flatYear[month - 1]; i++) {
                if (stamp <= timeStamp(year, month, i, 23, 59, 59, flatYear, leapYear)) {
                    day = i;
                    break;
                }
            }
        }
        return day;
    }

    public int getHour(int year, int month, int day, long stamp, int flatYear[], int leapYear[]) {
        int hour = 0;
        if (year == 1970) {
            for (int i = 8; i <= 24; i++) {
                if (stamp <= timeStamp(year, month, day, i, 59, 59, flatYear, leapYear)) {
                    hour = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i <= 24; i++) {
                if (stamp <= timeStamp(year, month, day, i, 59, 59, flatYear, leapYear)) {
                    hour = i;
                    break;
                }
            }
        }
        return hour;
    }

    public int getMinute(int year, int month, int day, int hour, long stamp, int flatYear[], int leapYear[]) {
        int minute = 0;
        for (int i = 0; i <= 59; i++) {
            if (stamp <= timeStamp(year, month, day, hour, i, 59, flatYear, leapYear)) {
                minute = i;
                break;
            }
        }
        return minute;
    }

    public int getSecond(int year, int month, int day, int hour, int minute, long stamp, int flatYear[], int leapYear[]) {
        int second = 0;
        for (int i = 0; i < 59; i++) {
            if (stamp == timeStamp(year, month, day, hour, minute, i, flatYear, leapYear)) {
                second = i;
                break;
            }
        }
        return second;
    }
}
