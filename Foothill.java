/* Please replace:  Assignment 2 (17/20) */
 
public class Foothill
{
  
   public static void main (String[] args)
   {
      int k;
      Student student;
     
      Student[] myClass = { new Student("smith","fred", 95),
         new Student("bauer","jack",123),
         new Student("jacobs","carrie", 195),
         new Student("renquist","abe",148),
         new Student("3ackson","trevor", 108),
         new Student("perry","fred",225),
         new Student("loceff","fred", 44),
         new Student("stollings","pamela",452),
         new Student("charters","rodney", 295),
         new Student("cassar","john",321),
      };
     
      // instantiate a StudArrUtilObject
      StudentArrayUtilities myStuds = new StudentArrayUtilities();
     
      // we can add students manually and individually:
      myStuds.addStudent( new Student("Bartman", "Petra", 102) );
      myStuds.addStudent( new Student("Charters","Rodney", 295));
      myStuds.addStudent( new Student("Carey", "Philip", 898));
     
      // add the students shown above in "myClass" to this array, using this loop:
      for (k = 0; k < myClass.length; k++)
         myStuds.addStudent( myClass[k] );
     
      //Also add a new student with out of range instance members:
      myStuds.addStudent( new Student("9A", "7X", 1500));
     
      // print out the current array:
      System.out.println( myStuds.toString("Unsorted Student List Before: "));
     
      // Now let's remove the "bad" student:
      student = myStuds.removeStudent();
      System.out.println("Removed Student: " + student);
     
      // do our first array sort (using default):
      myStuds.arraySort();
      System.out.println( myStuds.toString("Sorting by default (last name): "));
     
      Student.setSortKey(Student.SORT_BY_FIRST);
      myStuds.arraySort();
      System.out.println( myStuds.toString("Sorting Students by first name: "));
     
      Student.setSortKey(Student.SORT_BY_POINTS);
      myStuds.arraySort();
      System.out.println( myStuds.toString("Sorting by total points: "));
     
      // test median value of the current myStuds class:
      // Note: if we dont' know whether myStuds has an even or odd
      // number of students, we can print out the appropriate header using:
      if ( myStuds.getNumStudents() % 2 == 1.0 )
      {
          System.out.println("Median of oddClass = "
          +  myStuds.getMedianDestructive() + "\n");
      }
      else
      {
         System.out.println("Median of evenClass = "
               +  myStuds.getMedianDestructive() + "\n");
      }
     
      // add another student and see it we print out the correct header:
      myStuds.addStudent( new Student("Carey", "Pushkin", 654));
      System.out.println( myStuds.toString("Sorting by total points: "));
      // test median value of this myStuds class:
      if ( myStuds.getNumStudents() % 2 == 1.0 )
      {
          System.out.println("Median of oddClass = "
          +  myStuds.getMedianDestructive() + "\n");
      }
      else
      {
         System.out.println("Median of evenClass = "
               +  myStuds.getMedianDestructive() + "\n");
      }
     
      // add yet another student and see if we print out the correct header:
      myStuds.addStudent( new Student("Bumgarner", "Madison", 478));
     
      Student.setSortKey(Student.SORT_BY_LAST);
      myStuds.arraySort();
      System.out.println( myStuds.toString("Sorting Students by last name: "));
     
      // test median value of this myStuds class (after sorting by first name!):
      if ( myStuds.getNumStudents() % 2 == 1.0 )
      {
          System.out.println("Median of oddClass = "
          +  myStuds.getMedianDestructive() + "\n");
      }
      else
      {
         System.out.println("Median of evenClass = "
               +  myStuds.getMedianDestructive() + "\n");
      }
     
      // various tests of removing and adding too many students
      for (k = 0; k < 100; k++)
      {
         if ( (student = myStuds.removeStudent()) != null)
            System.out.print("Removed Student:  " + student);
         else
         {
            System.out.println("\nEmpty after " + k + " removes.");
            break;
         }
      }
 
      for (k = 0; k < 100; k++)
      {
         if (!myStuds.addStudent(new Student("first", "last", 22)))
         {
            System.out.println("\nFull after " + k + " adds.");
            break;
         }
      }
     
   }
}

class Student
{
   private String lastName;
   private String firstName;
   private int totalPoints;
 
   public static final String DEFAULT_NAME = "zz-error";
   public static final int DEFAULT_POINTS = 0;
   public static final int MAX_POINTS = 1000;
   public static final int SORT_BY_FIRST = 88;
   public static final int SORT_BY_LAST = 98;
   public static final int SORT_BY_POINTS = 108;
   public static final int SORT_KEY_MAX = 200;
   static private int sortCriteria = SORT_BY_LAST;
 
   // constructor requires parameters - no default supplied
   public Student( String last, String first, int points )
   {
      if ( !setLastName( last ) )
         lastName = DEFAULT_NAME;
      if ( !setFirstName( first ) )
         firstName = DEFAULT_NAME;
      if ( !setPoints( points ) )
         totalPoints = DEFAULT_POINTS;
   }
 
   public String getLastName() { return lastName; }
   public String getFirstName() { return firstName; }
   public int getTotalPoints() { return totalPoints; }
   public static int getSortKey() {return sortCriteria; }
 
   public boolean setLastName( String last )
   {
      if ( !validString( last ) )
         return false;
      lastName = last;
      return true;
   }
 
   public boolean setFirstName( String first )
   {
      if ( !validString( first ) )
         return false;
      firstName = first;
      return true;
   }
 
   public boolean setPoints( int pts )
   {
      if ( !validPoints( pts ) )
         return false;
      totalPoints = pts;
      return true;
   }
  
   // again, the teacher used switch and & case instead of if statements:
   static boolean setSortKey( int key )
   {
      switch (key)
      {
      case SORT_BY_FIRST:
      case SORT_BY_LAST:
      case SORT_BY_POINTS:
         sortCriteria = key;
         return true;
      default:
        // should never get here, but compiler wants it here or after switch
         return false;
      }
   }
 
   // Use switch & case for Sort (see below) which is more concise than my version
   public int compareTwoStudents( Student array )
   {
      switch (sortCriteria)
      {
      case SORT_BY_FIRST:
         return this.getFirstName().compareToIgnoreCase(array.getFirstName());
      case SORT_BY_LAST:
         return this.getLastName().compareToIgnoreCase(array.getLastName());
      case SORT_BY_POINTS:
         return this.getTotalPoints() - array.getTotalPoints();
      default:
         return 0;
      }
   }
 
   public String toString()
   {
      String resultString;
 
      resultString = " " + lastName
         + ", " + firstName
         + " points: " + totalPoints
         + "\n";
      return resultString;
   }
 
  
   private static boolean validString( String testStr )
   {
      if ( testStr != null && Character.isLetter( testStr.charAt(0) ) )
         return true;
      return false;
   }
 
   private static boolean validPoints( int testPoints )
   {
      if (testPoints >= 0 && testPoints <= MAX_POINTS)
         return true;
      return false;
   }
}
 
class StudentArrayUtilities
{
   public static final int MAX_STUDENTS = 20;
   private Student[] theArray;
   private int numStudents = 0;
     
   // first make a default constructor for this class:
   public StudentArrayUtilities()
   {
      theArray = new Student[MAX_STUDENTS];
      numStudents = 0;
   }
  
   // Allow the client to retrieve the number of students:
   public int getNumStudents() { return numStudents; }
  
   // This mutator allows us to add a student - will need to change this
   // a bit to eliminate bad student instance members ...
   public boolean addStudent( Student stud )
   {
      if ( stud == null || numStudents > MAX_STUDENTS - 1 )
         return false;
      theArray[numStudents] = stud;
      numStudents = numStudents + 1;
      return true;
   }
  
   // This method allows us to remove the last student in the array (when called)
   public Student removeStudent()
   {
      Student stud;
     
      if ( numStudents == 0 )
         return null;
      numStudents = numStudents - 1;
      stud = theArray[numStudents];
      return stud;
   }
 
   // Print the array of students along with a title passed from main():
   public void printArray( String title )
   {
      String output = "";
     
      // build the output string from the entire array of Students:
      for ( int k = 0; k < numStudents; k++ )
         output += " "+ theArray[k].toString();
     
      System.out.println( title +"\n"+ output );
   }
 
   // returns true if a modification was made to the array
   private boolean floatLargestToTop( int top)
   {
      boolean changed = false;
      Student temp;
      // compare with client call to see where the loop stops
      for ( int k = 0; k < top; k++ )
         if ( theArray[k].compareTwoStudents( theArray[k+1] ) > 0)
         {
            temp = theArray[k];
            theArray[k] = theArray[k+1];
            theArray[k+1] = temp;
            changed = true;
         }
      return changed;
   }
  
   public void arraySort()
   {
      for ( int k = 0; k < numStudents; k++ )
         // compare with method definition to see where inner loop stops
         if ( !floatLargestToTop( numStudents-1-k ) )
            return;
   }
  
   public double getMedianDestructive()
   {
      double med=0.0;  // default median value is 0.0
      // variable needed to preserve the initially passed sort key
      int savedSortKey;
      // assign the current sort key to the this variable:
      savedSortKey = Student.getSortKey();
      // switch to the sort by points sort key:
      Student.setSortKey( Student.SORT_BY_POINTS );
      if ( numStudents == 0 )
         med = 0.0;
      else if ( numStudents == 1 )
      {
         med = theArray[0].getTotalPoints();
      }
      // for an odd number of students if we divide by 2 the Modulus is 1.0:
      else if ( numStudents % 2 == 1.0 )
      {
         int x = numStudents/2;
         med = theArray[x].getTotalPoints();
      }
      // for an even number of students if we divide by 2 the Modulus is 0.0:
      else if ( numStudents % 2 == 0.0 )
      {
         int x = numStudents/2;
         med = (( theArray[x-1].getTotalPoints() )
               +  (theArray[x].getTotalPoints() ))/2.0;
      }
      // restore the original sort key before exiting:
      Student.setSortKey( savedSortKey );
      return med;
   }
 
   public String toString( String title )
   {
      String resultString = "";
      String theArrayString = "";
 
      for( int k = 0; k < numStudents; k++ )
      {
      theArrayString += theArray[k].getLastName()
         + ", " + theArray[k].getFirstName()
         + " points: " + theArray[k].getTotalPoints()
         + "\n";
      }
      resultString = title + "\n" + theArrayString;
      return resultString;
   }
}