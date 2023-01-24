public class Day implements Cloneable, Comparable<Day> {
	
	private int year;
	private int month;
	private int day;
    private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}   

    public void set(String sDay) //Set year,month,day based on a string like 01-Jan-2022
    {		
        String[] sDayParts = sDay.split("-");
        this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
        this.year = Integer.parseInt(sDayParts[2]); 
        this.month = MonthNames.indexOf( sDayParts[1] )/3 + 1;
    }

    public Day(String sDay) {
        set(sDay);
    } //Constructor, simply call set(sDay)

	public Day(int ayear, int amonth, int aday){
		this.year = ayear;
		this.month = amonth;
		this.day = aday;
	}

    @Override
    public String toString() {		
        return day+"-"+ MonthNames.substring( (month-1)*3 , month*3 ) + "-"+ year; // (month-1)*3,(month)*3
    }

    @Override
    public Day clone() {
        Day copy= null ;
        try {
            copy = (Day) super.clone() ;
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }

	@Override
	public int compareTo(Day another){
		
		if(this.year<another.year){
			return -1;
		}
		else if(this.year>another.year){
			return 1;
		}
		else{
			if(this.month<another.month){
				return -1;
			}
			else if(this.month > another.month){
				return 1;
			}
			else{
				if(this.day < another.day){
					return -1;
				}
				else if(this.day > another.day){
					return 1;
				}
				else{
					return 0;
				}
			}
		}
	}

	public void advance(){
		if(Day.valid(this.year, this.month, this.day+1)){
			this.day++;
		}
		else{
			if(Day.valid(this.year, this.month+1, 1)){
				this.month++;
				this.day = 1;
			}
			else{
				this.year++;
				this.month = 1;
				this.day = 1;
			}
		}
	}

	public static int countDiff(Day d1, Day d2){
		Day d3 = d1.clone();
		int count = 0;
		while(d3.compareTo(d2)!=1){
			count++;
			d3.advance();
		}
		return count;
	}

}